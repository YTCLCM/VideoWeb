package com.liucm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.liucm.bean.Video;

@Mapper
public interface VideoMapper {
	public int insertVideo(Video video);
	
	public int deleteVideoByVideoId(int videoId);
	
	public int updateVideoViewSumByVideoId(int videoId);
	
	public int updateVideoPpSumByVideoId(int videoId);
	
	public int updateVideoStateByVideoId(@Param(value = "videoId")int videoId,@Param(value="stateId")int stateId);
	
	public int updateVideoByVideoId(Video video);
	
	@Select("select count(*) from tb_video")
	public int selectVideoSum();
	
	public Video selectOneByVideoId(int videoId);
	
	public int selectVideoCount();
	
	public List<Video> selectVideoLikeNameAndLimit(String likeName,int curPage,int pageSize);
	
	public List<Video> selectVideoListByAjax(@Param(value = "curIndex")int curIndex,@Param(value = "pageSize")int pageSize);
	
	public List<Video> selectExistVideoListByAjax(@Param(value = "curIndex")int curIndex,@Param(value = "pageSize")int pageSize);
	
	public List<Video> selectVideoByUserId(int userId);
	
	public List<Video> selectEnableVideoByUserId(int userId);
	
	public List<Video> selectDisableVideoByUserId(int userId);
	
	public List<Video> selectVerifyingVideoByUserId(int userId);
	
	public List<Video> selectVerifiedVideoByUserId(int userId);
	
	public List<Video> selectVerifyFalseVideoByUserId(int userId);
	
	public List<Video> selectVideoByPPNum(int pageSize);
}
