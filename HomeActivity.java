package com.danielyan.mycourses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.danielyan.mycourses.Model.Category;
import com.danielyan.mycourses.Model.Course;
import com.danielyan.mycourses.adapter.CategoryAdapter;
import com.danielyan.mycourses.adapter.CourseAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    RecyclerView categoryRecycler, courseRecycler;
    CategoryAdapter categoryAdapter;
   static CourseAdapter courseAdapter;
   static List<Course> courseList = new ArrayList<>();
   static List<Course> fullCoursesList = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1,"Игры"));
        categoryList.add(new Category(2,"Сайты"));
        categoryList.add(new Category(3,"Языки"));
        categoryList.add(new Category(4,"Прочее"));

        setCategoryRecycler(categoryList);


        courseList.add(new Course(1,"java","Профессия Java\nразработчик","01.06.22","начальный","2500 rub","#424345","Данный курс предназначен для тех, кто только начинает изучать Java. Мы начнем с самых низов: компиляция и запуск Java-программ, синтаксис языка, система типов, основы объектно-ориентированного программирования. Далее обсудим наиболее важные классы стандартной библиотеки, включая нововведения Java 8.",3));
       courseList.add(new Course(2,"python","Профессия Python\nразработчик","10.06.22","начальный","2500 rub","#747B2B","Онлайн-курс по Python предназначен для начинающих разработчиков и поможет освоить один из самых распространенных языков программирования, с помощью которого можно создавать сайты, ботов, Desktop-приложения, а также обрабатывать и анализировать большие объемы данных",3));
        courseList.add(new Course(3,"back_end"," Backend\nразработчик","15.06.22","начальный","3000 rub","#4476D6","Специалисты в backend-разработке решают сложные задачи, берут на себя большую нагрузку и ответственность, но результат их работы не всегда виден. Backend-курсы подойдут целеустремленным и усидчивым новичкам в разработке, которые умеют искать, находить и систематизировать знания.",2));
        courseList.add(new Course(4,"front_end","Frontend\nразработчик","01.07.22","начальный","3000 rub","#F16A51","Frontend - то, что пользователь видит на экране смартфона или компьютера, когда открывает веб-страницу или приложение, с чем взаимодействует. Компоненты создания клиентской стороны:",2));
        courseList.add(new Course(5,"full_stack","FullSteck\nразработчик","15.07.22","начальный","3500 rub","#0D0F29","Этот курс подойдет для всех желающих - как для тех, кто хочет стать профессионалом в разработке Web приложений, так и для тех, кто просто хочет заниматься этим в качестве хобби и зарабатывать на этом - никакого опыта программирования не требуется.",2));
        courseList.add(new Course(6,"node_js","Профессия NodeJS\nразработчик","12.07.22","начальный","2000 rub","#FFE307","Обучающая программа рассчитана на начинающих пользователей, которые хотят освоить Node JS. Весь материал рассказан простым для человека языком с упором на практику." ,2));
       courseList.add(new Course(7,"unity","Unity\nразработчик","10.07.22","начальный","1800 rub","#C8384B","Курс Unity предназначен для того, чтобы ребенок взглянул на игры со стороны инженера-разработчика, познакомился с терминами и классификацией игр. Настройка объектов, префабов, анимации, программирование – являются основными задачами, которые должен решить разработчик игр.",1));

       fullCoursesList.addAll(courseList);

        setCourseRecycler(courseList);


    }

    public void openShoppingCart(View view){

        Intent cart =new Intent(this,CartActivity.class);
        startActivity(cart);

    }
    public void openSettings(View view){

        Intent settings =new Intent(this,SettingsActivity.class);
        startActivity(settings);

    }
    public void openAbout(View view){

        Intent About =new Intent(this,AboutActivity.class);
        startActivity(About);

    }
    public void openContacts(View view){

        Intent home =new Intent(this,ContactsActivity.class);
        startActivity(home);

    }




    private void setCourseRecycler(List<Course> courseList) {

        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        courseRecycler = findViewById(R.id.courseRecycler);
        courseRecycler.setLayoutManager(layoutManager);

        courseAdapter = new CourseAdapter(this,courseList);
        courseRecycler.setAdapter(courseAdapter);

    }

    private void setCategoryRecycler(List<Category> categoryList) {
        RecyclerView.LayoutManager layoutManager =new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        categoryRecycler = findViewById(R.id.categoryRecycler);
        categoryRecycler.setLayoutManager(layoutManager);

        categoryAdapter = new CategoryAdapter(this,categoryList);
        categoryRecycler.setAdapter(categoryAdapter);
    }

    public static void showCorusesByCategory(int category){

        List<Course> filterCourses = new ArrayList<>();

        courseList.clear();
        courseList.addAll(fullCoursesList);

        for (Course c: courseList){
            if (c.getCategory()== category)
                filterCourses.add(c);
        }
        courseList.clear();
        courseList.addAll(filterCourses);

        courseAdapter.notifyDataSetChanged();


    }
}