package com.sermeg.model;

import java.util.*;

public interface SerMegDAO_interface {
	public void insert(SerMegVO serMegVO);
	public void update(SerMegVO serMegVO);
	public void delete(Integer messageNo);
	public SerMegVO findByPrimaryKey(Integer messageNo);
	public List<SerMegVO> getAll();
}
