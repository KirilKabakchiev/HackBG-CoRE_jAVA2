package exceptions1.extendedHM;

import java.util.HashMap;

@SuppressWarnings("serial")
public class ExtendedHashMap<K, V> extends HashMap<K, V> {

    private static final boolean DEFAULT_NULL_KEYS = false;
    private boolean areNullKeysAllowed;

    public ExtendedHashMap() {
        this(DEFAULT_NULL_KEYS);
    }

    public ExtendedHashMap(boolean areNullKeysAllowed) {

        setAreNullKeysAllowed(areNullKeysAllowed);
    }

    public boolean AreNullKeysAllowed() {
        return areNullKeysAllowed;
    }

    public void setAreNullKeysAllowed(boolean areNullKeysAllowed) {
        this.areNullKeysAllowed = areNullKeysAllowed;
    }

    @Override
    public V get(Object key) {
        validKey(key);
        V val = (V) super.get(key);
        return val;
    }

    @Override
    public V put(K key, V value) {
        validKey(key);
        V val = super.put(key, value);
        return val;
    }

    private void validKey(Object key) {
        if (key == null && areNullKeysAllowed == false) {
            throw new NullUsedForKeyException();
        }
    }

}
