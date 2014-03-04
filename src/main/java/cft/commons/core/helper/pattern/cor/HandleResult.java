package cft.commons.core.helper.pattern.cor;

/**
 * 
 * @author daniel
 */
public class HandleResult {
	public HandleResult() {
	}

	public HandleResult(boolean succeeded) {
		this.succeeded = succeeded;
	}

	private boolean succeeded;

	/**
	 * 是否成功，如果成功返回true
	 * 
	 * @return boolean
	 */
	public boolean isSuccessful() {
		return succeeded;
	}

	/**
	 * 设置成功标记，如果设置为true表示链表执行到此为止，不会再执行下一个节点（任务）
	 * 
	 * @param b
	 *            boolean
	 */
	public void setSuccessful(boolean b) {
		succeeded = b;
	}

}
