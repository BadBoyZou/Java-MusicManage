package com.zsq.manage;

import java.util.Scanner;

public class Test {
	// 主菜单
	public void mainMenu() {
		System.out.println("***************************************");
		System.out.println("                 **主菜单**                      ");
		System.out.println("                 1--播放列表管理    ");
		System.out.println("                 2--播放器管理    ");
		System.out.println("                 0--退出   ");
		System.out.println("***************************************");

	}

	// 播放列表管理菜单
	public void playListMenu() {
		System.out.println("*******************************************************");
		System.out.println("                 **播放列表管理**    ");
		System.out.println("                 1--将歌曲添加到主播放列表    ");
		System.out.println("                 2--将歌曲添加到普通播放列表    ");
		System.out.println("                 3--通过歌曲id查询播放列表中的歌曲    ");
		System.out.println("                 4--通过歌曲名称查询播放列表中的歌曲    ");
		System.out.println("                 5--修改播放列表中的歌曲    ");
		System.out.println("                 6--删除播放列表中的歌曲    ");
		System.out.println("                 7--显示播放列表中的所有歌曲    ");
		System.out.println("                 9--返回上一级菜单   ");
		System.out.println("*******************************************************");
	}

	// 播放器菜单
	public void playerMenu() {
		System.out.println("*******************************************************");
		System.out.println("                 **播放器管理**    ");
		System.out.println("                 1--向播放器添加播放列表    ");
		System.out.println("                 2--从播放器删除播放列表    ");
		System.out.println("                 3--通过名字查询播放列表信息    ");
		System.out.println("                 4--显示所有播放列表名称    ");
		System.out.println("                 9--返回上一级菜单   ");
		System.out.println("*******************************************************");
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		Test test = new Test();
		int input1,input2,input3;
		// 创建一个播放列表容器（播放器）
		PlayListCollection plc = new PlayListCollection();
		// 创建主播放列表
		PlayList mainPlayList = new PlayList("主播放列表");
		// 将主播放列表添加到播放器
		plc.addPlayList(mainPlayList);
		//普通播放列表
		PlayList favouritePlayList=null;
		while (true) {
			test.mainMenu();
			System.out.println("请输入对应数字进行操作：");
			input1=sc.nextInt();
			if (input1 == 0) {
				break;
			}
			switch (input1) {
			case 1:
				while (true) {
					test.playListMenu();
					System.out.println("请输入对应数字对播放列表进行操作：");
					input2=sc.nextInt();
					if (input2 == 9)
						break;
					switch (input2) {
					case 1:
						System.out.println("将歌曲添加到主播放列表");
						System.out.println("请输入要添加的歌曲的数量：");
						int count=sc.nextInt();
						for (int i = 1; i <= count; i++) {
							System.out.println("请输入第"+i+"首歌曲信息：");
							System.out.println("请输入歌曲id：");
							String sId = sc.next();
							System.out.println("请输入歌曲名称：");
							String sName = sc.next();
							System.out.println("请输入歌曲演唱者：");
							String sSinger = sc.next();
							Song song = new Song(sId, sName, sSinger);
							mainPlayList.addToPlayList(song);
						}
						break;
					case 2:
						System.out.println("将歌曲添加到普通播放列表");
						System.out.println("请输入要添加的播放列表名称：");
						String sName=sc.next();
						favouritePlayList=plc.searchPlayListByName(sName);
						if (favouritePlayList==null) {
							System.out.println("该播放列表不存在，请先创建该播放列表！");
						}else {
							System.out.println("请输入要添加的歌曲数量：");
							int count1=sc.nextInt();
							for (int i =1; i <= count1; i++) {
								System.out.println("请输入第"+i+"歌曲的id：");
								String strId = sc.next();
								//首先判断该id的歌曲是否在主播放列表存在
								Song song=mainPlayList.searchSongbyId(strId);
								if(song==null){
									//如果歌曲不存在，则创建新的添加，并且添加到主播放列表
									System.out.println("该歌曲在主播放列表不存在，继续输入歌曲的其他信息！");
									System.out.println("请输入歌曲名称：");
									String strName=sc.next();
									System.out.println("请输入演唱者：");
									String strSinger=sc.next();
									//创建一个Song类的对象
									song=new Song(strId,strName,strSinger);
									//分别将歌曲添加到普通播放列表和主播放列表
									favouritePlayList.addToPlayList(song);
									mainPlayList.addToPlayList(song);
								}else{
									//如果歌曲存在于主播放列表，则直接添加到现在的播放列表
									favouritePlayList.addToPlayList(song);
								}
							}
						}
						break;
					case 3:
						System.out.println("通过歌曲id查询播放列表中的歌曲");
						System.out.println("请输入要查询的播放列表名称：");
						String steName = sc.next();
						PlayList playList = plc.searchPlayListByName(steName);
						if (playList == null) {
							System.out.println("该播放列表不存在，请先创建该播放列表");
							break;
						}else {
							System.out.println("请输入歌曲的id：");
							String steId = sc.next();
							Song song=playList.searchSongbyId(steId);
							if (song == null) {
								System.out.println("该歌曲在"+steName+"中不存在");
							}else {
								System.out.println("该歌曲的信息为：");
								System.out.println(song);
							}
						}
						
						break;
					case 4:
						System.out.println("通过歌曲名称查询播放列表中的歌曲");
						System.out.println("请输入要查询的播放列表名称：");
						String steName1 = sc.next();
						PlayList playList1 = plc.searchPlayListByName(steName1);
						if (playList1 == null) {
							System.out.println("该播放列表不存在，请先创建该播放列表");
							break;
						}else {
							System.out.println("请输入歌曲的名称：");
							String steNameString = sc.next();
							Song song=playList1.searchSongbyName(steNameString);
							if (song == null) {
								System.out.println("该歌曲在"+steName1+"中不存在");
							}else {
								System.out.println("该歌曲的信息为：");
								System.out.println(song);
							}
						}
						break;
					case 5:
						System.out.println("修改播放列表中的歌曲");
						System.out.println("请输入要查询的播放列表名称：");
						String strName1 = sc.next();
						PlayList playList2 = plc.searchPlayListByName(strName1);
						if (playList2 == null) {
							System.out.println("该播放列表不存在，请先创建该播放列表");
							break;
						}else {
							System.out.println("请输入歌曲的id：");
							String steId = sc.next();
							Song song=playList2.searchSongbyId(steId);
							if (song == null) {
								System.out.println("该歌曲在"+strName1+"中不存在");
							}else {
								System.out.println("请输入新歌曲的id：");
								String idString = sc.next();
								System.out.println("请输入新歌曲的名称：");
								String nameString = sc.next();
								System.out.println("请输入演唱者名称：");
								String singerString = sc.next();
								Song song2 = new Song(idString, nameString, singerString);
								if (playList2.equals("主播放列表")) {
									mainPlayList.updateSong(steId, song2);
								}else {
									playList2.updateSong(idString, song2);
								}
							}
						}
						break;
					case 6:
						System.out.println("删除播放列表中的歌曲");
						System.out.println("请输入要查询的播放列表名称：");
						String strName = sc.next();
						PlayList playList3 = plc.searchPlayListByName(strName);
						if (playList3 == null) {
							System.out.println("该播放列表不存在，请先创建该播放列表");
							break;
						}else {
							System.out.println("请输入歌曲的id：");
							String steId = sc.next();
							Song song=playList3.searchSongbyId(steId);
							if (song == null) {
								System.out.println("该歌曲在"+strName+"中不存在");
							}else if(playList3.equals("主播放列表")) {
								mainPlayList.deleteSong(steId);
								playList3.deleteSong(steId);
							}
						}
						
						break;
					case 7:
						System.out.println("显示播放列表中的所有歌曲");
						System.out.println("请输入要显示歌曲信息的播放列表：");
						String stringName = sc.next();
						PlayList pList=plc.searchPlayListByName(stringName);
						if (pList == null) {
							System.out.println("该播放列表不存在，请先创建该播放列表！");
							break;
						}else{
							pList.displayAllSong();
						}
						break;
					default:						
						break;
					}
				}
				break;
			case 2:
				while (true) {
					test.playerMenu();
					System.out.println("请输入对应数字对播放器进行操作：");
					input3=sc.nextInt();
					if (input3 == 9) {
						break;
					}
					switch (input3) {
					case 1:
						System.out.println("向播放器添加播放列表：");
						System.out.println("请输入播放列表名称：");
						String playerName = sc.next();
						//创建一个新的播放列表对象
						favouritePlayList=new PlayList(playerName);
						//将播放列表添加到播放器Map
						plc.addPlayList(favouritePlayList);
						break;
					case 2:
						System.out.println("从播放器删除播放列表");
						System.out.println("请输入要删除的播放列表名称：");
						String strName = sc.next();
						if (strName.equals("主播放列表")) {
							System.out.println("主播放列表不能删除！");
							break;
						}
						PlayList pList=plc.searchPlayListByName(strName);
						if(pList==null){
							System.out.println("该播放列表不存在！");
						}else{
							//存在则删除
							plc.deletePlayList(pList);
						}
						break;
					case 3:
						System.out.println("通过名称查询歌曲信息");
						System.out.println("请输入歌曲名称：");
						String strName1 = sc.next();
						PlayList pList2=plc.searchPlayListByName(strName1);
						if(pList2==null){
							System.out.println("该播放列表不存在！");
						}else{
							//显示该播放列表名称及其中的所有歌曲
							System.out.println("该播放列表存在！");
							System.out.println("该播放列表的名称为："+strName1);
							pList2.displayAllSong();
						}
						break;
					case 4:
						System.out.println("显示所有播放列表名称");
						plc.displayListName();
						
						break;
					case 9:				
						
						break;
					default:
						
						break;
					}
				}
				
			break;
				
			}
		}
		
	}

}
