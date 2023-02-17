package com.order.model;

import java.util.List;

import com.orderDetail.model.OrderDetailVO;


public interface OrderDAO_interface {
    public int insert(OrderVO orderVO);
    public void update(OrderVO orderVO);
    public void delete(Integer orderNo);
    public OrderVO findByPrimaryKey(Integer orderNo);
    public List<OrderVO> findByMemNo(Integer memNo);
    public List<OrderVO> getAll();
   
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
