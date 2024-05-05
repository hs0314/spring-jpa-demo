package heesu.me.springjpademo.service;

import heesu.me.springjpademo.domain.Delivery;
import heesu.me.springjpademo.domain.Member;
import heesu.me.springjpademo.domain.Order;
import heesu.me.springjpademo.domain.OrderItem;
import heesu.me.springjpademo.domain.item.Item;
import heesu.me.springjpademo.repository.ItemRepository;
import heesu.me.springjpademo.repository.MemberRepository;
import heesu.me.springjpademo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;


    /**
     * 주문 생성
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count){

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order =Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        // Order 엔티티 내의 cascade 설정때문에 배송, 주문상품에 대한 정보 persist 가능
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     * @param orderId
     */
    @Transactional
    public void cancleOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);

        // 주문 취소
        // JPA 더티 체킹(상태변화감지)을 통해서 데이터 변경이 발생한 엔티티에 update를 자동으로 날려줌
        order.cancel();
    }

    /*
    public List<Order> findOrders(OrderSearch params){

    }

     */
}
