package cft.commons.core.helper.pattern.observer;
 
import java.util.Observable;

/**
 * @author daniel
 * 
 */
public abstract class AbleImp extends Observable {
	private boolean clearFlag = false;

	public AbleImp() {
	}

	/**
	 * 监控一个属性对象，一般在setter方法属性变化的时候设置
	 *
	 * @param observedObj Object－－属性对象
	 */
	public void observeOneObject(Object observedObj) {
		this.setChanged();
		this.notifyObservers(observedObj);
	}

	/**
	 * 增加一个观察者
	 *
	 * @param oimp ObserverImp－－观察者
	 */
	public void addOneObserver(ObserverImp oimp) {
		this.addObserver(oimp);
	}

	/**
	 * 删除一个受控对象
	 *
	 * @param oimp ObserverImp－－观察者
	 */
	public void removeOneObserver(ObserverImp oimp) {
		this.deleteObserver(oimp);
	}

	/**
	 * 取消所有的监控，在取消之前触发受控消息
	 */
	public void cancelAllObservers() {
		this.notifyObservers();
		this.clearChanged();
		this.deleteObservers();
		clearFlag = true;
	}

	protected void finalize() throws Throwable {
		if (clearFlag == false) {
			this.clearChanged();
			this.deleteObservers();
		}
	}
}
