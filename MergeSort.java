int[] mergeSort(int[] array) {
 if (array.length <= 1)
 return array;
 int middle = array.length / 2;
 int firstHalf = mergeSort(array[0..middle - 1]);
 int secondHalf = mergeSort(
 array[middle..array.length - 1]);
 return merge(firstHalf, secondHalf);
}