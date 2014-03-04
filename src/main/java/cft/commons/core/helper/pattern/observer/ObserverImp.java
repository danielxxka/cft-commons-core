package cft.commons.core.helper.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者抽类
 * 
 * @author daniel */

public abstract class ObserverImp
    implements Observer {
  public ObserverImp() {
  }

  public void update(Observable o, Object arg) {
    dealEvent( (AbleImp) o, arg);
  }

  /**
   * 当监控对象发生变化时候，触发处理事件
   *
   * @param ableImp AbleImp－－受监控的类
   * @param observedObj Object－－受监控的属性（对象）
   */
  public abstract void dealEvent(AbleImp ableImp, Object observedObj);
}
