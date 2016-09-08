package generalassemb.ly.lesson.sortingalgorithms;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by alanjcaceres on 8/29/16.
 */
public class Algorithms {

    public int[] mergeSort(int[] array){
        /*
            Code in your merge sort algorithm
            It should perform the algorithm "in-place".
            Meaning, DO NOT MAKE A COPY OF THE ARGUMENT

            func mergeSort( var a as array )
                if ( n == 1 ) return a

                var l1 as array = a[0] ... a[n/2]
                var l2 as array = a[n/2+1] ... a[n]

                l1 = mergesort( l1 )
                l2 = mergesort( l2 )

                return merge( l1, l2 )
            end func
         */
        if(array.length == 1) {
            return array;
        }

        int mid = array.length/2;

        int[] array1 = new int[mid];
        int[] array2 = new int[array.length - array1.length];

        for (int i = 0; i < mid; i++) {
            array1[i] = array[i];
        }

        for (int i = 0; i < array2.length; i++) {
            array2[i] = array[mid + i];
        }

        int[] mergeArray1 = mergeSort(array1);
        int[] mergeArray2 = mergeSort(array2);

        array = merge(mergeArray1,mergeArray2);

        //System.out.println(Arrays.toString(array));
        return array;
    }

    private int[] merge(int[] firstArray, int[] secondArray){
        int[] result = null;
        
        /*
            Code in your merging algorithm here
            You should put your merged result into the result array.
            *Note* You need to make sure you properly initialize your
            result array.

            func merge( var a as array, var b as array )
                var c as array

                 while ( a and b have elements )
                      if ( a[i] > b[j] )
                           add b[j] to the end of c
                           increment b[j] from b
                      else
                           add a[i] to the end of c
                           increment a[i] from a
                 while ( a has elements )
                      add a[i] to the end of c
                      remove a[i] from a
                 while ( b has elements )
                      add b[j] to the end of c
                      remove b[j] from b
                 return c
            end func
        */

        int[] mergedArray = new int[firstArray.length + secondArray.length];

        int i = 0;
        int j = 0;
        int k = 0;

        while(i < firstArray.length && j < secondArray.length) {
            if (firstArray[i] > secondArray[j]) {
                mergedArray[k] = secondArray[j];
                j++;
                k++;
            } else{
                mergedArray[k] = firstArray[i];
                i++;
                k++;
            }
        }
        while (i < firstArray.length){
            mergedArray[k] = firstArray[i];
            i++;
            k++;
        }
        while (j < secondArray.length){
            mergedArray[k] = secondArray[j];
            j++;
            k++;
        }

        result = mergedArray;
        //System.out.println(Arrays.toString(result));

        return result;
    }


}
