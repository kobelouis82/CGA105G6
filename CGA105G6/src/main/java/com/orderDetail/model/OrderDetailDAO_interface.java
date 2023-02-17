package com.orderDetail.model;

import java.util.List;

public interface OrderDetailDAO_interface {
    public void insert(OrderDetailVO orderDetailVO);
    public void update(OrderDetailVO orderDetailVO);
    public void delete(Integer orderNo);
    public OrderDetailVO findByPrimaryKey(Integer orderNo);
    public List<OrderDetailVO> getAll();
    public List<OrderDetailVO> getAllOrderNo(Integer orderNo);
    //�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
//  public List<EmpVO> getAll(Map<String, String[]> map); 
}
