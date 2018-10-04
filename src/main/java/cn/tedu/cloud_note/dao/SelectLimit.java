package cn.tedu.cloud_note.dao;

import java.io.Serializable;

public class SelectLimit implements Serializable {

        private static final long serialVersionUID = 1L;

        private String keyword;
        private Integer offset;
        private Integer rows;
        
        
        public String getKeyword() {
                return keyword;
        }
        public void setKeyword(String keyword) {
                this.keyword = keyword;
        }
        public Integer getOffset() {
                return offset;
        }
        public void setOffset(Integer offset) {
                this.offset = offset;
        }
        public Integer getRows() {
                return rows;
        }
        public void setRows(Integer rows) {
                this.rows = rows;
        }
        public static long getSerialversionuid() {
                return serialVersionUID;
        }
        
        
        @Override
        public String toString() {
                return "SelectLimit [keyword=" + keyword + ", offset=" + offset + ", rows=" + rows + "]";
        }
}
