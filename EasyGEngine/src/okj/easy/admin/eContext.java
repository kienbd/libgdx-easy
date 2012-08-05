package okj.easy.admin;


import java.util.Iterator;

import org.ege.assets.DataLoader;
import org.ege.assets.StyleLoader;
import org.ege.assets.StyleLoader.StyleParameter;
import org.ege.utils.DataSSD;
import org.ege.utils.Factory;
import org.ege.utils.GraphicsHelper;
import org.ege.utils.Pool;
import org.ege.utils.exception.EasyGEngineRuntimeException;
import org.ege.widget.StyleAtlas;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ResolutionFileResolver.Resolution;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class eContext {
	public final AssetManager manager;
	
	ObjectMap<String, Context> mContextMap = new ObjectMap<String, Context>();
	ObjectMap<String, Class<?>> mNoManageData = new ObjectMap<String, Class<?>>();
	
	static StyleAtlas mCurStyleAtlas = null;
	
	static TextureAtlas mCurAtlas = null;
	
	public eContext(){
		manager = new AssetManager();
		manager.setLoader(DataSSD.class,new DataLoader(new InternalFileHandleResolver())); 
	}
	
	/*******************************************************************************
	 * Style manager 
	 ********************************************************************************/

	public StyleAtlas styleQuery(String name){
		return mCurStyleAtlas = manager.get(name, StyleAtlas.class);
	}
	
	/**
	 * Safe Get
	 * @param styleName
	 * @param type
	 * @return
	 */
	public <T> T getStyle(String styleName,Class<T> type){
		if(mCurStyleAtlas == null)
			throw new EasyGEngineRuntimeException("Plz  use eAdmin.eContext.atlasQuery(...) mode first");
		return mCurStyleAtlas.get(styleName, type);
	}
	
	/**
	 * Unsafe get
	 * @param resourceName
	 * @param type
	 * @return
	 */
	public <T> T optional(String resourceName,Class<T> type){
		if(mCurStyleAtlas == null)
			throw new EasyGEngineRuntimeException("Plz  use eAdmin.eContext.atlasQuery(...) mode first");
		return mCurStyleAtlas.optional(resourceName, type);
	}
	
	public StyleAtlas getQueryStyle(){
		return mCurStyleAtlas;
	}

	public StyleAtlas getStyleAtlas(String name){
		return manager.get(name, StyleAtlas.class);
	}
	
	public void stopQuery(){
		mCurStyleAtlas = null;
	}
	
	public void setLoader(Resolution...resolutions){
		if(resolutions.length > 0){
			ResolutionFileResolver resolver = new ResolutionFileResolver(new InternalFileHandleResolver(), resolutions);
			manager.setLoader(StyleAtlas.class	, new StyleLoader(resolver));
		}else{
			manager.setLoader(StyleAtlas.class	, new StyleLoader(new InternalFileHandleResolver()));
		}
	}
	
	public void loadStyle(String dataPath){
		manager.load(dataPath, StyleAtlas.class);
	}
	
	public void loadStyle(String dataPath,boolean isUseSuffixFOrTExture){
		if(isUseSuffixFOrTExture)
			loadStyle(dataPath);
		else{
			String tmp = dataPath;
			tmp = tmp.replace(".json", ".png");
			StyleParameter param = new StyleParameter(tmp);
			manager.load(dataPath, StyleAtlas.class, param);
		}
	}
	
	public void loadStyle(String datapath,StyleParameter param){
		manager.load(datapath, StyleAtlas.class, param);
	}
	
	/*******************************************************
	 *	AssetManager method 
	 *******************************************************/
	
	public void unload(String dataPath){
		manager.unload(dataPath);
	}
	
	public boolean update(){
		return manager.update();
	}

	public boolean isLoaded(String dataPath){
		return manager.isLoaded(dataPath);
	}
	
	public boolean isLoaded(String dataPath,Class type){
		return manager.isLoaded(dataPath, type);
	}
	
	public void finishLoading(){
		manager.finishLoading();
	}
	
	public int getLoadedAssets(){
		return manager.getLoadedAssets();
	}
	
	public int getQueueAssets(){
		return manager.getQueuedAssets();
	}
	
	public float getProgress(){
		return manager.getProgress();
	}
	
	public <T> void load(String name,Class<T> type){
		manager.load(name, type);
	}

	public <T> void load(String name,Class<T> type,AssetLoaderParameters<T> param){
		manager.load(name, type,param);
	}
	
	public <T, P extends AssetLoaderParameters<T>> void setLoader(Class<T> type, AssetLoader<T, P> loader){
		manager.setLoader(type, loader);
	}
	
	/**
	 * get the Context list which manager is manage
	 * @return the array list of art
	 */
	public Iterator<Context> toArts(){
		return mContextMap.values();
	}
	
	/**
	 * Destroy the manager (game down)
	 */
	void dispose(){
		manager.dispose();
	}
	
	/**
	 * Clear all asset of the manager
	 */
	void superClear(){
		manager.clear();
	}
	
	public int totalData(){
		int size = 0;
		for (Iterator<Context> iterator = mContextMap.values(); iterator.hasNext();) {
			size += iterator.next().size();
		}
		return size;
	}
	
	/*******************************************************************************
	 * public Context manager method
	 ********************************************************************************/
	
	public void reloadContext(String contextName){
		mContextMap.get(contextName).reload();
	}
	
	
	public void unloadContext(String contextName){
		mContextMap.get(contextName).unload();
	}
	
	/**
	 * Remove all data which contain in the context from manager , even if that data hadn't been loaded(not recommended)
	 * @param context your context
	 */
	public void clearContext(String contextName){
		Context context = mContextMap.get(contextName);
		unloadContext(context);
		context.mDataMap.clear();
		context.mUnloadedData.clear();
		context.isTotallyUnloaded = false;
	}

	/**
	 * Destroy the given context and unload all it data from manager
	 * @param context your context
	 * @return true if success removed, otherwise false
	 */
	public void removeContext(String contextName){
		mContextMap.get(contextName).remove();
	}
	
	/**************************************************************
	 * Private context manage method
	 **************************************************************/
	/**
	 * Add a context to manager and also add it to object map for fast query
	 * @param context your context
	 * @return true if context != null and success load context , otherwise false
	 */
	void addContext(Context context){
		if(context != null && !mContextMap.containsValue(context,true)){
			mContextMap.put(context.name(), context);
		}
	}

	
	/**
	 * load the data to manager,just for context
	 * @param data
	 */
	<T> void loadContextData(String linkName,Class<T> clazz) {
		manager.load(linkName, clazz);
	}
	
	/**
	 * load the data to manager,just for context
	 * @param data
	 */
	 <T> void loadContextData(String linkName,Class<T> clazz,AssetLoaderParameters<T> param) {
		manager.load(linkName, clazz,param);
	}
	
	
	
	<T> void loadGraphicsData(String linkName,Data<T> data){
		if(data.param != null)
			loadContextData(linkName, data.clazz, data.param);
		else 
			loadContextData(linkName, data.clazz);
	}
	
	void loadContext(Context context){
		Iterator<String> assetList = context.mDataMap.keys();
		String tmp = null;
		while(assetList.hasNext()){
			tmp = assetList.next();
			loadGraphicsData(tmp, context.getContextData(tmp));
		}
	}
	
	void unloadContext(Context context){
		Iterator<String> dataName  = context.mDataMap.keys();
		while(dataName.hasNext())
			unloadDirectly(dataName.next());
	}
	
	void removeContext(Context context){
		unloadContext(context);
		mContextMap.remove(context.name);
	}
	
	/**
	 * Directly unload the given data from the manager(carefull when you call this method)
	 * @param data unLoaded data
	 */
	void unloadDirectly(String linkName){
		manager.unload(linkName);
	}
	
	/***************************************************************************
	 *Query
	 ***************************************************************************/
	
	/**
	 * Find your art by its name
	 * @param name art's name
	 * @return found art(maybe null)
	 */
	public Context findContextByName(String name){
		return mContextMap.get(name);
	}
	
	/**
	 * Find context data by its name
	 * @param linkName
	 * @param clazz
	 * @return
	 */
	public <T> T findDataByName(String linkName,Class<T> clazz){
		return manager.get(linkName, clazz);
	}
	
	/***************************************************************************
	 * Query function
	 ***************************************************************************/
	
	public TextureAtlas atlasQuery(String name){
		mCurAtlas = manager.get(name, TextureAtlas.class);
		return mCurAtlas;
	}
	
	public TextureRegion findGRegionByName(String regionName){
		if(mCurAtlas == null)
			throw new EasyGEngineRuntimeException("Plz use eAdmin.egraphics.atlasQuery(...) first");
		return mCurAtlas.findRegion(regionName);
	}

	public TextureRegion findGRegionByName(String regionName,int index){
		if(mCurAtlas == null)
			throw new EasyGEngineRuntimeException("Plz use eAdmin.egraphics.atlasQuery(...) first");
		return mCurAtlas.findRegion(regionName,index);
	}

	public TextureRegion[] findGRegionsByName(String regionName){
		if(mCurAtlas == null)
			throw new EasyGEngineRuntimeException("Plz use eAdmin.egraphics.atlasQuery(...) first");
		return GraphicsHelper.regionConvert(mCurAtlas.findRegions(regionName));
	}
	
	 /*******************************************************************************
	  * Pool Manager 
	  ********************************************************************************/
	 
	 private static final ObjectMap<Class<?>, Pool<?>> mPools = new ObjectMap<Class<?>, Pool<?>>();
	 private static Pool mCurrentPool;
	 
	 public Pool poolQuery(Class type){
		 mCurrentPool = mPools.get(type);
		 if(mCurrentPool == null)
			 throw new EasyGEngineRuntimeException("You haven't make any pool for class : " + type.getName());
		 return mCurrentPool;
	 }
	 
	 public <T> void makePool(final Class<T> type,Factory<T> factory){
		 mPools.put(type, new Pool<T>(factory) );
	 }
	
	 public <T> void makePool(int initCapacity,Class<T> type,Factory<T> factory){
		 mPools.put(type, new Pool<T>(initCapacity,factory));
	 }
	 
	 public <T> T newObject(){
		 return (T) mCurrentPool.obtain();
	 }

	 public <T> T newObject(Object...objects){
		 return (T) mCurrentPool.obtain(objects);
	 }

	 public <T> T newObject(Class<T> type){
		 return (T) mPools.get(type).obtain();
	 }

	 public <T> T newObject(Class<T> type,Object...objects){
		 return (T) mPools.get(type).obtain(objects);
	 }
	 
	 public <T> void free(T obj){
		 mCurrentPool.free(obj);
	 }

	 public <T> void free(Array<T> objs){
		 mCurrentPool.free(objs);
	 }

	 public <T> void free(Class<T> type,T obj){
		 ((Pool<T>)mPools.get(type)).free(obj);
	 }

	 public <T> void free(Class<T> type,Array<T> objs){
		 ((Pool<T>)mPools.get(type)).free(objs);
	 }

	 public int poolSize(){
		 return mCurrentPool.size();
	 }
	 
	 public <T> int poolSize(Class<T> type){
		 return mPools.get(type).size();
	 }
	 
	 public void clearPool(){
		 mCurrentPool.clear();
	 }
	 
	 public void clearPool(Class<?> type){
		 mPools.get(type).clear();
	 }
}
