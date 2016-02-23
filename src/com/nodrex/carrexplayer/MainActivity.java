package com.nodrex.carrexplayer;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import com.nodrex.carrexplayer.custom.CustomRecyclerLayout;
import com.nodrex.carrexplayer.custom.CustomRecyclerView;

import android.app.ActionBar;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MainActivity extends Activity implements OnClickListener , OnCompletionListener{

	private View pref;
	private View pause;
	private View next;
	private CustomRecyclerView list;
	
	private List<String> musics = new ArrayList<String>();
	private  MediaPlayer mediaPlayer = new MediaPlayer();
	private static int currentMusicINdex = 0;

	public static int getCurrentMusicINdex() {
		return currentMusicINdex;
	}

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initActivity();
		
		getMusics();
		if(musics == null) return;
		if(musics.size() == 0) return;
        if(mediaPlayer !=null) mediaPlayer.setOnCompletionListener(this);
		
        intViews();
	}
	
	protected void onResume() {
		super.onResume();
		startplaying();
	}
	
	private void startplaying() {
		if(musics == null || musics.size() == 0) return;
		play(currentMusicINdex);
	}

	private void getMusics(){
		File downloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
		FileExtensionFilter fileExtensionFilter = new FileExtensionFilter();
        if (downloads.listFiles(fileExtensionFilter).length > 0) {
            for (File file : downloads.listFiles(fileExtensionFilter)) {
            	if(file == null) continue;
            	String path = file.getPath();
                musics.add(path);
            }
        }else Util.toast(this, "There is no music");
	}
	
	/**
     * Class to filter files which are having .mp3 extension
     * */
    private class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }

	private void initActivity(){
		Util.keepScreenOn(this);
		Window window = getWindow();
		Util.hideStatusBar(window);
		Util.hideNavigationButtons(window);
		ActionBar actionBar = getActionBar();
		if(actionBar != null)actionBar.hide();
	}
	
	private void intViews() {
		pref = findViewById(R.id.prev);
		pause = findViewById(R.id.pause);
		next = findViewById(R.id.next);
		list = (CustomRecyclerView) findViewById(R.id.list);
		if(pref != null)pref.setOnClickListener(this);
		if(pause != null)pause.setOnClickListener(this);
		if(next != null)next.setOnClickListener(this);
		initRecyclerView();
	}

	private void initRecyclerView() {
		if(list == null) return;
		CustomRecyclerLayout linearLayoutManager = new CustomRecyclerLayout(this);
		if(linearLayoutManager != null){
			linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
			list.setLayoutManager(linearLayoutManager);
		}
		list.setAdapter(new MusicAdapter(this,musics));
	}
	
	public void onClick(View v) {
		if(v == null) return;
		int id = v.getId();
		if(id == R.id.prev){
			play(currentMusicINdex - 1);
		}else if (id == R.id.next){
			play(currentMusicINdex + 1);
		}else{
			if(mediaPlayer != null )mediaPlayer.pause();
		}
	}
	
	public void play(int musicIndex){
        try {
        	if(musics == null || musics.size() == 0) return;
        	String dataSource= null;
        	try {
        		if(musicIndex < 0) musicIndex = musics.size() -1;
        		else if(musicIndex > musics.size() -1)musicIndex = 0;
				dataSource = musics.get(musicIndex);
			}catch (Exception e) {
				Util.toast(this, "play -> Exception: " + e.toString());
			}
        	if(dataSource == null) return;
        	mediaPlayer.reset();
        	mediaPlayer.setDataSource(dataSource);
			mediaPlayer.prepare();
	        mediaPlayer.start();
	        currentMusicINdex = musicIndex;
	        list.getAdapter().notifyDataSetChanged();
	        list.smoothScrollToPosition(currentMusicINdex);
		}  catch (Exception e) {
			Util.toast(this, "play -> Exception: " + e.toString());
		}
	}

	public void onCompletion(MediaPlayer mediaPlayer) {
		play(currentMusicINdex + 1);
	}
	
	protected void onStop() {
		super.onStop();
		if(mediaPlayer != null) {
			try {
				mediaPlayer.reset();
				mediaPlayer.stop();
				mediaPlayer.release();
				mediaPlayer = null;
			} catch (Exception e) {}
		}
	}
	
}
