#include <string.h>
#include <stdio.h>
int check_length(char arr[]) {
  int length = strlen(arr);
  printf("Length of String `%s` : %d ", arr, length);
  printf("\n");
  return length;
}