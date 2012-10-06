package okj.easy.graphics.graphics2d;

import org.ege.utils.Factory;
import org.ege.utils.Pool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.LongMap;
import com.badlogic.gdx.utils.ObjectMap;

public class NWorld {
	private final LongMap<NSprite> mSpriteMap = new LongMap<NSprite>(100);

	private final Pool<NSprite> mSpritePool;

	// =============================================
	// manager management

	private final NManager mMainManager = newManager();
	private final LongMap<NManager> mManagerMap = new LongMap<NManager>(3);

	// =============================================
	// sprite def manager

	// count the current number of sprite owning that sprite def
	private final LongMap<Integer> mSpriteDefCountMap = new LongMap<Integer>(3);
	private final ObjectMap<String, NSpriteDef> mSpriteDefMap = new ObjectMap<String, NSpriteDef>(3);

	public NWorld(int poolSizeOfNSprite) {
		mSpritePool = new Pool<NSprite>(poolSizeOfNSprite, new Factory<NSprite>() {
			@Override
			public NSprite newObject () {
				return createNSprite();
			}

			@Override
			public NSprite newObject (Object... objects) {
				return null;
			}
		});
	}

	/************************************************************
	 * Sprite associate method
	 ************************************************************/

	public NSprite newSprite () {
		NSprite sprite = mSpritePool.obtain();
		mMainManager.manage(sprite);
		mSpriteMap.put(sprite.address, sprite);
		return sprite;
	}

	public NSprite newSprite (NManager manager) {
		NSprite sprite = mSpritePool.obtain();
		manager.manage(sprite);
		mSpriteMap.put(sprite.address, sprite);
		return sprite;
	}

	public NSprite newSprite (long address) {
		NManager manager = mManagerMap.get(address);
		if (manager == null)
			return null;

		NSprite sprite = mSpritePool.obtain();
		manager.manage(sprite);
		mSpriteMap.put(sprite.address, sprite);
		return sprite;
	}

	// =====================================

	private final NSprite createNSprite () {
		final NSprite sprite = new NSprite(CreateNSprite(), this, mMainManager);
		sprite.isPooled = false;
		return sprite;
	}

	/**
	 * Remove sprite from manage map , and reset it put it to the pool {@link NSprite}
	 */
	void poolSprite (NSprite sprite) {
		mSpritePool.freeNoReset(sprite);
		mSpriteMap.remove(sprite.address);
		sprite.manager.mSpriteList.removeValue(sprite, true);
	}

	/**
	 * delete given sprite from sprite map, and sprite pool
	 */
	void deleteSprite (NSprite sprite) {
		if (!sprite.isPooled()) {
			mSpriteMap.remove(sprite.address);
			sprite.manager.mSpriteList.removeValue(sprite, true);
		} else
			mSpritePool.delete(sprite);
	}

	// ===============================================
	// native method

	/**
	 * Create a native reference of sprite
	 * 
	 * @return native address of sprite
	 */
	private final native long CreateNSprite ();

	/************************************************************
	 * Manager associate method
	 ************************************************************/

	public NManager newManager () {
		NManager manager = new NManager(CreateManager(), this);
		mManagerMap.put(manager.address, manager);
		return manager;
	}

	public void delManager (long address) {
		if (address == mMainManager.address)
			return;

		directDeleteManager(mManagerMap.get(address));
	}

	public void delManager (NManager manager) {
		if (manager == mMainManager)
			return;

		directDeleteManager(manager);
	}

	/**
	 * Remove sprite from manage map , and reset it put it to the pool. Only use for manager
	 * {@link NManager}
	 */
	void poolSprite (NManager manager) {
		final Array<NSprite> list = manager.mSpriteList;

		for (NSprite sprite : list) {
			sprite.resetWithoutWorldCallback();
			mSpritePool.freeNoReset(sprite);
			mSpriteMap.remove(sprite.address);
		}

		list.clear();
		mManagerMap.remove(manager.address);
	}

	/**
	 * Direct delete manager
	 * 1. check manager = null
	 * 2. dispose sprite
	 * 3. clear manager list
	 * 4. dispose manager {@link NManager}
	 */
	final void directDeleteManager (NManager manager) {
		final Array<NSprite> list = manager.mSpriteList;

		for (NSprite sprite : list) {
			sprite.disposeWithoutWorldCallback();
			mSpriteMap.remove(sprite.address);
		}

		list.clear();

		DisposeManager(manager.address);
	}

	// =================================================
	// native

	private final native long CreateManager ();

	private final native void DisposeManager (long address);

	/**************************************************************
	 * NSpriteDef
	 **************************************************************/

	public void newSpriteDef (String name) {
		final NSpriteDef def = new NSpriteDef(name, CreateSpriteDef(), this);
		mSpriteDefMap.put(name, def);
		mSpriteDefCountMap.put(def.address, 0);
	}

	public void delSpriteDef (String name) {
		mSpriteDefMap.get(name).dispose();
	}

	public NSpriteDef get (String name) {
		return mSpriteDefMap.get(name);
	}

	/**
	 * Return the number of NSprite is using this NSpriteDef for calculate
	 * bonding
	 */
	public int getSpriteOfSpriteDef (String name) {
		return mSpriteDefCountMap.get(mSpriteDefMap.get(name).address);
	}

	// ===================================================

	void spriteAddSpriteDef (NSprite sprite, String spriteDefName) {
		final long spriteAddress = sprite.address;
		final NSpriteDef def = mSpriteDefMap.get(spriteDefName);
		final long spriteDefAddress = def.address;
		NSpriteAddNSpriteDef(spriteAddress, spriteDefAddress);

		if (sprite.def != null)
			mSpriteDefCountMap.put(sprite.def.address,
					mSpriteDefCountMap.get(sprite.def.address) - 1);
		mSpriteDefCountMap.put(spriteDefAddress, mSpriteDefCountMap.get(spriteDefAddress + 1));
		sprite.def = def;
	}

	boolean deleteSpriteDef (NSpriteDef def) {
		if (mSpriteDefCountMap.get(def.address) == 0) {
			mSpriteDefCountMap.remove(def.address);
			mSpriteDefMap.remove(def.name);
			return true;
		}
		Gdx.app.log("EasyGameEngine  ", "You can't delete NSpriteDef with name : " + def.name
				+ "  because still have NSprite associate with it");
		return false;
	}

	// =================================================
	// native

	private final native long CreateSpriteDef ();

	private final native long NSpriteAddNSpriteDef (long spriteAddress, long spriteDefAddress);
}
