package cft.commons.core.helper.pattern.cor;

import java.util.List;

/**
 * 
 * @author daniel *
 */
public class ChainNode {
	private List chain;

	private NodeHandler handler;

	/**
	 * ChainLinkNode
	 * 
	 * @param s
	 *            NodeHandle 策略（链接工作）对象
	 */
	public ChainNode(NodeHandler handle) {
		this.handler = handle;
		// chain.add(this);
	}

	private ChainNode next() {
		// Where this link is in the chain:
		int location = chain.indexOf(this);
		if (!end()) {
			return (ChainNode) chain.get(location + 1);
		}
		// Indicates a programming error (thus
		// doesn't need to be a checked exception):
		throw new RuntimeException("end of chain");
	}

	private boolean end() {
		int location = chain.indexOf(this);
		return location + 1 >= chain.size();
	}

	HandleResult execute(IReq req) { // 迭代
		HandleResult r = handler.handle(req);
		if (r.isSuccessful() || end()) {
			return r;
		}
		return next().execute(req);
	}

	void setChain(List chain) {
		this.chain = chain;
	}

}
