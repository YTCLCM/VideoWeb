package com.liucm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.liucm.bean.Video;
import com.liucm.util.Page;

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
	
	public List<Integer> getAllVideoId();
	
	public Video selectOneByVideoId(int videoId);
	
	public int selectVideoCount();
	
	public int selectVerifyVideoCount();
	
	public List<Video> selectVideoLikeNameAndLimit(String likeName,int curPage,int pageSize);
	
	public List<Video> selectVideoListByAjax(@Param(value = "curIndex")int curIndex,@Param(value = "pageSize")int pageSize);
	
	public List<Video> selectExistVideoListByAjax(@Param(value = "curIndex")int curIndex,@Param(value = "pageSize")int pageSize);
	
	public List<Video> selectVideoByTypeAjax(@Param(value = "curIndex")int curIndex,@Param(value = "pageSize")int pageSize,@Param(value="videoTypeId")int videoTypeId);
	
	public List<Video> selectVideoByPage(@Param(value = "page")Page<Video> page,@Param(value = "stateId") int stateId);
	
	public List<Video> selectVideoByUserId(int userId);
	
	public List<Video> selectVideoByUserIdAndVideoStateId(@Param(value = "userId")int userId,@Param(value = "videoStateId")int videoStateId);
	
	public List<Video> selectVideoByPPNum(int pageSize);

	public List<Video> selectRecommendVideo(@Param(value = "curIndex")int curIndex,@Param(value = "pageSize")int pageSize,@Param(value = "userId")int userId);
}
