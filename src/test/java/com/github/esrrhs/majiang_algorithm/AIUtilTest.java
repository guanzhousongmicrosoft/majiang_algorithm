package com.github.esrrhs.majiang_algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AIUtilTest
{
	@BeforeAll
	static void loadTables()
	{
		HuUtil.load();
		AIUtil.load();
	}

	@Test
	void documentedOutDecisionRemainsStable()
	{
		assertEquals(MaJiangDef.stringToCard("东"),
				AIUtil.outAI(cards("1万,2万,2万,1条,1条,东"), cards("1万")));
	}

	@Test
	void documentedChiDecisionRemainsStable()
	{
		List<Integer> hand = cards("1万,2万,2万,1条,1条,1筒,2筒,4筒,4筒,5筒");
		List<Integer> gui = cards("1万");
		int target = MaJiangDef.stringToCard("3筒");

		assertTrue(AIUtil.chiAI(hand, gui, target, MaJiangDef.stringToCard("2筒"), MaJiangDef.stringToCard("4筒")));
		assertIterableEquals(cards("1筒,2筒"), AIUtil.chiAI(hand, gui, target));
	}

	@Test
	void documentedPengAndGangDecisionsRemainStable()
	{
		assertTrue(AIUtil.pengAI(cards("1万,2万,2万,1条,1条,2筒,4筒,4筒"), cards("1万"),
				MaJiangDef.stringToCard("2万"), 0.d));
		assertTrue(AIUtil.gangAI(cards("1万,2万,2万,2万,3万,4万,4筒,4筒"), cards("1万"),
				MaJiangDef.stringToCard("2万"), 1.d));
	}

	private static List<Integer> cards(String cards)
	{
		return MaJiangDef.stringToCards(cards);
	}
}
