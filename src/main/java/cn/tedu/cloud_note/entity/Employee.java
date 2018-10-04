package cn.tedu.cloud_note.entity;

import java.io.Serializable;

public class Employee implements Serializable {

        private static final long serialVersionUID = 1L;
        
        private Integer id;
        private String name;
        private int age;
        
        public Integer getId() {
                return id;
        }
        public void setId(Integer id) {
                this.id = id;
        }
        public String getName() {
                return name;
        }
        public void setName(String name) {
                this.name = name;
        }
        public int getAge() {
                return age;
        }
        public void setAge(int age) {
                this.age = age;
        }
        public static long getSerialversionuid() {
                return serialVersionUID;
        }
        
        @Override
        public String toString() {
                return "Employee [id=" + id + ", name=" + name + ", age=" + age + "]";
        }
}
