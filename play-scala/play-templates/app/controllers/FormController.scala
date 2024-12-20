package controllers

import com.google.inject.Inject
import models.{
  ComplexFormCustomField,
  InputFormWithConstraints,
  MultipleFieldsForm,
  SimpleForm
}
import play.api.i18n.I18nSupport
import play.api.libs.json.Json
import play.api.mvc.{
  AbstractController,
  Action,
  AnyContent,
  ControllerComponents
}

class FormController @Inject() (cc: ControllerComponents)
  extends AbstractController(cc)
  with I18nSupport {

  def simpleForm: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.Baeldung.FormTemplate(SimpleForm.form))
  }

  def formPost(): Action[AnyContent] = Action { implicit request =>
    val form = SimpleForm.form.bindFromRequest().get
    Ok(form.toString)
  }

  def multipleFieldsForm: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.Baeldung.MultipleFieldsFormTemplate(MultipleFieldsForm.form))
  }

  def multipleFieldsFormPost(): Action[AnyContent] = Action {
    implicit request =>
      val form = MultipleFieldsForm.form.bindFromRequest().get
      Ok(form.toString)
  }

  def formWithConstraints: Action[AnyContent] = Action { implicit request =>
    Ok(
      views.html.Baeldung.FormTemplateWithConstraints(
        InputFormWithConstraints.form
      )
    )
  }

  def formWithConstraintsPost(): Action[AnyContent] = Action {
    implicit request =>
      InputFormWithConstraints.form
        .bindFromRequest()
        .fold(
          { formWithError =>
            BadRequest(
              views.html.Baeldung.FormTemplateWithConstraints(formWithError)
            )
          },
          { data => Ok(Json.toJson(data)) }
        )
  }

  def simpleFormPostWithErrors(): Action[AnyContent] = Action {
    implicit request =>
      SimpleForm.form
        .bindFromRequest()
        .fold(
          { formWithError =>
            BadRequest(
              views.html.Baeldung.FormTemplateWithErrors(formWithError)
            )
          },
          { data => Ok(Json.toJson(data)) }
        )
  }

  def complexForm: Action[AnyContent] = Action { implicit request =>
    Ok(views.html.Baeldung.ComplexFormTemplate(ComplexFormCustomField.form))
  }

  def complexFormPostWithErrors(): Action[AnyContent] = Action {
    implicit request =>
      ComplexFormCustomField.form
        .bindFromRequest()
        .fold(
          { formWithError =>
            BadRequest(views.html.Baeldung.ComplexFormTemplate(formWithError))
          },
          { data => Ok(Json.toJson(data)) }
        )
  }

}
