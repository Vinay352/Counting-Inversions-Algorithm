/*
 * Picture.java
 *
 * Version:
 *     $2$
 */

/**
 * CSCI-665
 *
 *  Aim:  An algorithm to sort the given array/order in desired order
 *          using divide and conquer approach, which gives us a sorted array
 *          and inversion count as a result.
 *
 *   Complexity of our Implementation: O( n * log(n) )
 *
 *   Very similar to Merge Sort algorithm.
 *
 *
 *   @author: Omkar Morogiri,om5692
 *   @author: Vinay Jain,vj9898
 *
 *
 */


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Picture {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ));
        // take input for number of people in line
        int n = Integer.parseInt( br.readLine() );

        Person[] input = new Person[n];

        for( int i = 0; i < n ; i++ ){
            String[] temp = br.readLine().strip().split(" "); // take input for n number of age and height
            input[i] = new Person( Integer.parseInt( temp[0] ), Float.parseFloat( temp[1] ) );
        }

//        for( int i = 0; i < n ; i++ ){
//            System.out.println( input[i].age + " " + input[i].height );
//        }

//        ReturnPair rp = sortAndCount( input, 0, n - 1 );
//        System.out.println(rp.count);
//        System.out.println(rp.personArray;

        System.out.println( sortAndCount( input, 0, n - 1 ) );

    }

    // function to sort out array in desired order and count inversions
    private static int sortAndCount(Person[] input, int left, int right) {

        // if only one element, return 0, since no inversion is required for one element
        if ( left == right ){
            // return new ReturnPair(0, input);
            return 0;
        }

        int mid = left + (int) Math.floor( ( right - left ) / 2 );

        // ReturnPair rpLeft = sortAndCount(input, left, mid);

        int leftCount = sortAndCount(input, left, mid); // sort left of array
        int rightCount = sortAndCount(input, mid + 1, right); // sort right half of array
        int mergeCount = mergeCount(input, left, mid, right); // merge left and right half of array and count inversions

        return leftCount + rightCount + mergeCount; // return total inversion count
        // return 0;
    }

    // function to sort array in desired order and count inversions
    private static int mergeCount(Person[] input, int left, int mid, int right) {

        int leftSize = mid - left + 1; // size of left half of array
        int rightSize = right - mid; // size of right half of array

        Person[] leftArray = new Person[leftSize]; // array to hold left half of original array elements
        Person[] rightArray = new Person[rightSize]; // array to hold right half of original array elements

        for( int i = 0; i < leftSize; i++ ){
            leftArray[i] = input[i + left];
            // System.out.println("left " + leftArray[i].height);
        }

        // System.out.println("-----------");

        for( int i = 0 ; i < rightSize; i++ ){
            rightArray[i] = input[i + leftSize + left];
            // System.out.println("right " + rightArray[i].height);
        }

        int i = leftSize, j = rightSize;
        int k = left; // start point for merging left and right half into original array

        int count = 0; // inversion count

        // while there are elements in both left and right half of arrays
        while ( i > 0 && j > 0 ) {

            // if age of left array element is same as that of right array element
            if ( leftArray[leftSize - i].age == rightArray[rightSize - j].age ) {
                if ( leftArray[leftSize - i].age == 7 ){ // if it is = 7, order should be increasing height
                    if (leftArray[leftSize - i].height > rightArray[rightSize - j].height){
                        input[k] = rightArray[rightSize - j];
                        count += i; // increment inversion count
                        j--;
                    }
                    else{
                        input[k] = leftArray[leftSize - i];
                        i--;
                    }
                }
                else if ( leftArray[leftSize - i].age == 8 ){ // if it is = 8, order should be decreasing height
                    if (leftArray[leftSize - i].height < rightArray[rightSize - j].height){
                        input[k] = rightArray[rightSize - j];
                        count += i; // increment inversion count
                        j--;
                    }
                    else{
                        input[k] = leftArray[leftSize - i];
                        i--;
                    }
                }
            }
            // if age of left array element is different than that of right array element
            else if ( leftArray[leftSize - i].age != rightArray[rightSize - j].age ) {
                // if left value = 7 and right value = 8 or 44
                if ( leftArray[leftSize - i].age == 7 &&
                        ( rightArray[rightSize - j].age == 8 || rightArray[rightSize - j].age == 44 ) ){

                    input[k] = leftArray[leftSize - i];
                    i--;

                }
                // if left value = 8 and right value = 7 or 44
                else if ( leftArray[leftSize - i].age == 8 &&
                            ( rightArray[rightSize - j].age == 7 || rightArray[rightSize - j].age == 44 ) ){

                    input[k] = rightArray[rightSize - j];
                    count += i;
                    j--;

                }
                // if left value = 44
                else if( leftArray[leftSize - i].age == 44 ){
                    if( rightArray[rightSize - j].age == 7 ){ // if right value = 7
                        input[k] = rightArray[rightSize - j];
                        count += i;
                        j--;
                    }
                    else if( rightArray[rightSize - j].age == 8 ){ // if right value = 8
                        input[k] = leftArray[leftSize - i];
                        i--;
                    }
                }
            }
            k++;
        }

        // if there are elements left in left array
        while (i > 0) {
            input[k] = leftArray[leftSize - i];
            i--;
            k++;
        }

        // if there are elements left in right array
        while (j > 0) {
            input[k] = rightArray[rightSize - j];
            j--;
            k++;
        }

        return count; // return inversion count found while merging left and right half of array

    }

}
