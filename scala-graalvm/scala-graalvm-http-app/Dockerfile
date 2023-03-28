FROM findepi/graalvm:java11-all
RUN gu install native-image
WORKDIR /app
ADD target/scala-2.13/scala-graalvm-http-app-assembly-1.0-SNAPSHOT.jar httpgraalvmapp.jar
RUN native-image --static --verbose --allow-incomplete-classpath --report-unsupported-elements-at-runtime  --no-fallback -jar httpgraalvmapp.jar httpgraalvmapp
ENTRYPOINT ["/app/httpgraalvmapp"]

