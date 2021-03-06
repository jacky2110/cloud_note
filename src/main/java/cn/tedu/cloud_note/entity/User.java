package cn.tedu.cloud_note.entity;

import java.io.Serializable;
import java.util.List;

//参照cn_user表的定义
//属性名与字段名保持一致
//属性类型与字段类型一致
//实现序列化
public class User implements Serializable{

        private static final long serialVersionUID = 1L;

        private String cn_user_id;
        private String cn_user_name;
        private String cn_user_password;
        private String cn_user_token;
        private String cn_user_nick;

        // 添加属性，与其他实体类相关联
        private List<Book> books;
        
        // 添加get和set方法
        public List<Book> getBooks() {
                return books;
        }
        public void setBooks(List<Book> books) {
                this.books = books;
        }
        
        public String getCn_user_id() {
                return cn_user_id;
        }
        public void setCn_user_id(String cn_user_id) {
                this.cn_user_id = cn_user_id;
        }
        public String getCn_user_name() {
                return cn_user_name;
        }
        public void setCn_user_name(String cn_user_name) {
                this.cn_user_name = cn_user_name;
        }
        public String getCn_user_password() {
                return cn_user_password;
        }
        public void setCn_user_password(String cn_user_password) {
                this.cn_user_password = cn_user_password;
        }
        public String getCn_user_token() {
                return cn_user_token;
        }
        public void setCn_user_token(String cn_user_token) {
                this.cn_user_token = cn_user_token;
        }
        public String getCn_user_nick() {
                return cn_user_nick;
        }
        public void setCn_user_nick(String cn_user_nick) {
                this.cn_user_nick = cn_user_nick;
        }
        public static long getSerialversionuid() {
                return serialVersionUID;
        }

        @Override
        public String toString() {
                return "User [cn_user_id=" + cn_user_id + ", cn_user_name=" + cn_user_name + ", cn_user_password="
                                + cn_user_password + ", cn_user_token=" + cn_user_token + ", cn_user_nick="
                                + cn_user_nick + ", books=" + books + "]";
        }
}