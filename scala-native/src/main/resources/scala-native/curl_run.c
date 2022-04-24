#include <stdlib.h>
#include <curl/curl.h>
void function_pt(void *ptr, size_t size, size_t nmemb, void *stream){
    printf("%d", atoi(ptr));
}
int make_curl_call(char arr[])
{
  CURL *curl;
  CURLcode res;
  curl = curl_easy_init();
  if(curl) {
    curl_easy_setopt(curl, CURLOPT_URL, arr);
    curl_easy_setopt(curl, CURLOPT_FOLLOWLOCATION, 1L);
     res = curl_easy_perform(curl);
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, function_pt);
    if(res != CURLE_OK)
      fprintf(stderr, "curl_easy_perform() failed: %s\n",
              curl_easy_strerror(res));
     curl_easy_cleanup(curl);
  }
  return 0;
}