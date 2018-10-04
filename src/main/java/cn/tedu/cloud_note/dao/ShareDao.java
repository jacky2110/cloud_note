package cn.tedu.cloud_note.dao;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Share;

public interface ShareDao {
        // 声明一个函数，用于分享笔记（向cn_share表中插入一条数据）
        public int shareNote(Share share);
        
        // 声明一个函数，用于模糊搜索
        public List<Share> findLikeTitle(SelectLimit sl);
        
        // 声明一个函数，用于模糊搜索
        public List<Share> findLikeTitleMap(Map<String, Object> map);
}
