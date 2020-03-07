package com.zsq.manage;
/**
 * 	播放列表
 * @author Administrator
 *
 */

import java.util.ArrayList;
import java.util.List;

public class PlayList {
	private String playListName;	//播放列表名称
	private List<Song> musicList=null; //歌曲集合，用于存放歌曲
	
	public PlayList(String playListName) {
		this.playListName = playListName;
		musicList = new ArrayList<Song>();
	}
	public String getPlayListName() {
		return playListName;
	}
	public void setPlayListName(String playListName) {
		this.playListName = playListName;
	}
	public List<Song> getMusicList() {
		return musicList;
	}
	public void setMusicList(List<Song> musicList) {
		this.musicList = musicList;
	}
	/**
	 * 将歌曲添加到播放列表
	 * @param song 要添加的歌曲
	 */
	public void addToPlayList(Song song) {
		//排除重复添加
		boolean flag = false;
		for (Song song1 : musicList) {
			if (song1.equals(song)) {
				flag=true;break;
			}
		}
		if(flag){
			System.out.println("该歌曲已经存在于播放列表中，添加失败！");
		}else{
			musicList.add(song);
		}		
	}
	/**
	 * 根据id查找歌曲
	 * @param id 被查找的id
	 * @return	被查找的歌曲信息
	 */
	public Song searchSongbyId(String id) {
		Song song = null;
		//查询歌曲id是否有效
		for (Song song1 : musicList) {
			if (song1.getId().equals(id)) {
				song = song1;break;
			}
		}
		return song;
	}
	/**
	 * 	格局名称查询歌曲
	 * @param name 被查找的歌曲名称
	 * @return 被查找的歌曲信息
	 */
	public Song searchSongbyName(String name) {
		Song song = null;
		//查询歌曲id是否有效
		for (Song song1 : musicList) {
			if (song1.getName().equals(name)) {
				song = song1;break;
			}
		}
		return song;
	}
	/**
	 * 	根据id修改歌曲信息
	 * @param id 被查找的歌曲id
	 * @param song 新的歌曲信息
	 */
	public void updateSong(String id,Song song) {
		//根据id查找该歌曲
		Song song1 = searchSongbyId(id);
		if (song1 == null) {
			System.out.println("没有找到对应的歌曲");
		}else {
			//如果song1不为null，先删去旧的信息，再添加新的歌曲信息
			musicList.remove(song1);
			musicList.add(song);
			System.out.println("修改成功");
		}
	}
	/**
	 * 	根据id删除歌曲
	 * @param id 需要删除的歌曲id
	 */
	public void deleteSong(String id) {
		// 根据id查找该歌曲
		Song song = searchSongbyId(id);
		if (song == null) {
			System.out.println("没有找到对应的歌曲");
		} else {
			musicList.remove(song);
		}
	}
	/**
	 * 显示播放列表中的所有歌曲
	 */
	public void displayAllSong(){
		System.out.println("播放列表中的所有歌曲为：");
		for(Song song:musicList){
			System.out.println(song);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
