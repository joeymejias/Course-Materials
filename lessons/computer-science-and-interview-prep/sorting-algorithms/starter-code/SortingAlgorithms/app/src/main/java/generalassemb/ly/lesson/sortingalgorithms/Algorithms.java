package generalassemb.ly.lesson.sortingalgorithms;

/**
 * Created by alanjcaceres on 8/25/16.
 */
public class Algorithms {

    public int[] bubbleSort(int[] array){
        /*
            Code in your bubble sort algorithm
            It should perform the algorithm "in-place".
            Meaning, DO NOT MAKE A COPY OF THE ARGUMENT

            for i from 1 to N
                for j from 0 to N - 1
                    if a[j] > a[j + 1]
                        swap a[j] and a[j + 1]
            return N
         */

        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length-1; j++) {
                if (array[j] > array[j + 1]) { // change to array[j] < array[j + 1] for descending order
                    //swap logic
                    int tempNum = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tempNum;
                }
            }
        }

        return array;
    }

    public int[] insertionSort(int[] array){
        /*
            Code in your insertion sort algorithm
            It should perform the algorithm "in-place"
            Meaning, DO NOT MAKE A COPY OF THE ARGUMENT
         */

        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j-1]) {
                    int tempNum = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tempNum;
                }
            }
        }
        return array;
    }

}
