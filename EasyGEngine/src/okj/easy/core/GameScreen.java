package okj.easy.core;

import okj.easy.admin.Screen;
import okj.easy.admin.eAdmin;
import okj.easy.core.utils.Bridge;

public abstract class GameScreen extends Screen  {
	protected static  GameCore mGameCore;
	
	private boolean PAUSE = false;
	
	@Override
	public void show(GameCore gamecore) {
		if(this.PAUSE == true){
			resume();
			return;
		}
		
		mGameCore = gamecore;
			
		projection.setToOrtho(0,eAdmin.gameWidth(), 0,eAdmin.gameHeight() , -1, 1);
	
		batch.setProjectionMatrix(projection);
		
		onCreateLayout();
		onLoadResource();
	}

	/**
	 * This method will load the layout for User Interface 
	 * @return your layout
	 */	
	public abstract void onCreateLayout();
	
	
	/**
	 * This method for loading some add resource(maybe it is unnecessary if there loading sreen)
	 */		
	public abstract void onLoadResource();
	

	@Override
	public void resize(int width, int height) {
		onResize(width,height);
	}
	
	public abstract void onResize(int width,int height);

	
	@Override
	public void destroy(int destroyMode) {
		if(destroyMode == RELEASE){
			clearLayout();
			batch.flush();
			onDestroy();
		}else if(destroyMode == HIDE){
			pause();
		}
	}
	
	@Override
	public void resume() {
		focusLayout();
		this.PAUSE = false;
		onResume();
	}

	/**
	 * Call when the game is Resume from pause
	 */
	public abstract void onResume();	
	
	@Override
	public void pause() {
		unfocusLayout();
		this.PAUSE = true;
		onPause();
	}

	/**
	 * Call when the game is pause or when setScreen in HIDE mode
	 */
	public abstract void onPause();

	@Override
	public void update(float delta) {
		if(!PAUSE){
			onUpdate(delta);
			updateLayout(delta);
		}
	}

	public abstract void onUpdate(float delta); 
		

	@Override
	public void render(float delta) {
		onRender(delta);
	}

	/**
	 * render all stuff and the layout will be the highest layer
	 * @param delta Gdx.graphics.getDeltaTime()
	 */
	public abstract void onRender(float delta);

	/**
	 * Only be called when you call setScreen(RELEASE mode)
	 */
	public abstract void onDestroy();


	/**
	 * Set screen which lead to the new screen and the destroy mode of old screen
	 * @param screen new screen
	 * @param destroyMode destroy mode(RELEASE for totally destroy , HIDE -just make it invisible)
	 */
	protected void setScreen(Screen screen,int destroyMode) {
		mGameCore.setScreen(screen,destroyMode);
	}
	
	/************************************************************
	 * Bridge method
	 ************************************************************/
	public Bridge newBridge(Class<?> firstClass,Class<?> secondClass){
		return mGameCore.newBridge(firstClass, secondClass);
	}
	
	public Bridge newBridge(String name){
		return mGameCore.newBridge(name);
	}
	
}
