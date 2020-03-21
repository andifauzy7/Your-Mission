package com.example.yourmission;

import java.text.ParseException;
import java.util.ArrayList;

public class TaskData {
    private static String[] taskName = {
            "Buat Rancangan Database",
            "Membangun SRS Web Freelance",
            "Tugas Use Case Diagram",
            "Survey Statistika",
            "Proposal Himpunan",
            "Rapat Himpunan",
            "Kumpul Angkatan",
            "Tugas Statistika"
    };

    private static String[] taskDetails = {
            "Membuat rancangan database untuk website Freelancer",
            "SRS yang mencakup Usecase, database dan mockup",
            "Membuat use case diagram lengkap antara user dan admin",
            "Melakukan survey ke mahasiswa POLBAN mengenai aplikasi Freelance",
            "Merancang proposal program kerja Himpunan",
            "Rapat membahas kepengurusan Himpunan",
            "Kumpul Angkatan membahas mengenai Kunjungan Industri",
            "Memmbuat langkah Statistika Deskriptif"
    };

    private static String[] taskDeadline = {
            "12-APR-2020",
            "02-MAY-2020",
            "03-MAY-2020",
            "10-JUN-2020",
            "08-JUN-2020",
            "12-JUL-2020",
            "13-JUL-2020",
            "21-AUG-2020"
    };

    static ArrayList<Task> getListData() throws ParseException {
        ArrayList<Task> list = new ArrayList<>();
        for (int position = 0; position < taskName.length; position++) {
            Task task = new Task(taskName[position], taskDetails[position], taskDeadline[position]);
            list.add(task);
        }
        return list;
    }
}