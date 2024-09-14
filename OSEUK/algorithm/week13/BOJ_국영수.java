/**
 * BAEKJOON ONLINE JUDGE
 * 문제 이름 : 국영수
 * 문제 번호 : 10825
 * 난이도 : SILVER IV
 */

package baekjoon.sort.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class BOJ10825 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < N; i++){
            String[] arr = br.readLine().split(" ");

            String name = arr[0];
            int korean = Integer.parseInt(arr[1]);
            int english = Integer.parseInt(arr[2]);
            int math = Integer.parseInt(arr[3]);

            students.add(new Student(name, korean, english, math));
        }

        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {

                if (o1.getKorean() == o2.getKorean()){
                    if (o1.getEnglish() == o2.getEnglish()){
                        if (o1.getMath() == o2.getMath()){
                            return o1.getName().compareTo(o2.getName());
                        }
                        return o2.getMath() - o1.getMath();
                    }
                    return o1.getEnglish() - o2.getEnglish();
                }
                return o2.getKorean() - o1.getKorean();
            }
        });

        for (Student student : students){
            System.out.println(student.getName());
        }

    }
}

class Student{
    private String name;
    private int korean;
    private int english;
    private int math;

    public Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKorean() {
        return korean;
    }

    public void setKorean(int korean) {
        this.korean = korean;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }
}
