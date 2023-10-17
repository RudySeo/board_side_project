package sideproject.board.global.exception.configuration;

import sideproject.board.member.domain.Entity.Member;

public class ThreadLocalContext {
	private static ThreadLocal<Member> threadLocal = new ThreadLocal<>();

	public static void set(Member value) {
		threadLocal.set(value);
	}

	public static Member get() {
		return threadLocal.get();
	}

	public static void clear() {
		threadLocal.remove();
	}

}
