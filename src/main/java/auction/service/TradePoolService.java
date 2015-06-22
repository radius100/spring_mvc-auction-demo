package auction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.TradePool;
import auction.repository.TradePoolRepository;

@Transactional
@Service
public class TradePoolService {

	@Autowired
	TradePoolRepository tradePoolRepository;
	
	public List<TradePool> findItemTradeDetail(Item item) {
		
		return tradePoolRepository.findByItemOrderByAmountDesc(item);
	}

	public int findCurrentAmount(Item item) {
		
		TradePool tradePool = tradePoolRepository.findFirstByItemOrderByAmountDesc(item);
		
		return (null != tradePool)?tradePool.getAmount():-1;
	}
}
