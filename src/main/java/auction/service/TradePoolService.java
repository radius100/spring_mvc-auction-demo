package auction.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import auction.entity.Item;
import auction.entity.TradePool;
import auction.entity.User;
import auction.repository.ItemRepository;
import auction.repository.TradePoolRepository;
import auction.repository.UserRepository;

@Transactional
@Service
public class TradePoolService {

	@Autowired
	TradePoolRepository tradePoolRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ItemRepository itemRepository;

	// переименовать в getItemTradeDetail
	public List<TradePool> findItemTradeDetail(Item item) {

		return tradePoolRepository.findByItemOrderByAmountDesc(item);
	}

	// удалить так как функионал в билдере есть
	public int findCurrentAmount(Item item) {

		TradePool tradePool = tradePoolRepository.findFirstByItemOrderByAmountDesc(item);

		return (null != tradePool) ? tradePool.getAmount() : -1;
	}

	public String save(Principal principal, int id, String amount) {

		User user = userRepository.findOneByName(principal.getName());
		Item item = itemRepository.findOne(id);

		int intAmount = 0;

		try {
			intAmount = Integer.parseInt(amount);
		} catch (NumberFormatException e) {
			return "fail";
		}

		TradePool tradePool = new TradePool();
		tradePool.setItem(item);
		tradePool.setUser(user);
		tradePool.setAmount(intAmount);
		tradePool.setDate(new Date());

		tradePoolRepository.save(tradePool);

		return "ok";
	}
}
