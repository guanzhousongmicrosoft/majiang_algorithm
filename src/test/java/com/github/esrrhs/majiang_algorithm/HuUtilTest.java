package com.github.esrrhs.majiang_algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class HuUtilTest
{
	@BeforeAll
	static void loadTables()
	{
		HuUtil.load();
	}

	@Test
	void documentedHuExampleRemainsWinning()
	{
		assertTrue(HuUtil.isHu(cards("1万,1万"), MaJiangDef.stringToCard("1万")));
	}

	@Test
	void documentedTingExamplesRemainStable()
	{
		List<Integer> expected = cards("1万,1筒,4筒,东");
		List<Integer> hand = cards("1万,1万,1筒,3筒,2筒,2条,3条,4条,东,东");
		int gui = MaJiangDef.stringToCard("1筒");

		assertIterableEquals(expected, HuUtil.isTing(hand, gui));
		assertIterableEquals(expected, HuUtil.isTingExtra(hand, Collections.singletonList(gui)));
	}

	@Test
	void cardConversionHelpersRoundTrip()
	{
		List<Integer> cards = cards("1万,9筒,5条,东,白,菊");

		assertIterableEquals(cards, MaJiangDef.stringToCards(MaJiangDef.cardsToString(cards)));
		assertEquals("白", MaJiangDef.cardToString(MaJiangDef.stringToCard("白")));
	}

	private static List<Integer> cards(String cards)
	{
		return MaJiangDef.stringToCards(cards);
	}
}
