package Week03;

import java.util.Arrays;
import java.util.Random;

public class Main {


    static void main() {
        int[] arr = new int[]{1, 3, 2, 54, 6, 75, 3, 3, 54, 3434, 2345};
        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));


        Student[] students = new Student[10];

        for (int i = 0; i < 10; i++) {

            students[i] = new Student("name-" +  new Random().nextInt(0, 78), "lastName-"  + new Random().nextInt(0, 78));
        }
        System.out.println(Arrays.toString(students));

        System.out.println("-------------------------------------------");

        for (int i = 0; i < 10; i++) {
            System.out.println(students[i]);
        }

        Arrays.sort(students);
//        sort(students);
        System.out.println("-------------------------------------------");

        for (int i = 0; i < 10; i++) {
            System.out.println(students[i]);
        }


    }

    private static void sort(Student[] students) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (students[i].getName().compareTo(students[j].getName()) < 0) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }
    }


}
