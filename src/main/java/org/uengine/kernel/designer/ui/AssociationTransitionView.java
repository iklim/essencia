package org.uengine.kernel.designer.ui;

import org.metaworks.EventContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.essencia.modeling.ConnectorSymbol;
import org.uengine.kernel.graph.AssociationTransition;
import org.uengine.kernel.graph.Transition;
import org.uengine.modeling.IRelation;
import org.uengine.modeling.RelationPropertiesView;
import org.uengine.modeling.Symbol;

public class AssociationTransitionView extends TransitionView{
	
	public final static String SHAPE_ID = "OG.shape.bpmn.C_Association";
	
	public AssociationTransitionView(){
		
	}
	
	public AssociationTransitionView(IRelation relation){
		super(relation);
	}
	
	@ServiceMethod(callByContent=true, eventBinding=EventContext.EVENT_DBLCLICK)
	public Object properties() throws Exception {
		Transition transition;
		if(this.getRelation() == null)
			transition = new Transition();
		else
			transition = (Transition)this.getRelation();
		
		return new RelationPropertiesView(transition);
	}
	
	public static Symbol createSymbol() {
		Symbol symbol = new Symbol();
		symbol.setName("연관");
		
		return fillSymbol(symbol);
	}
	
	public static Symbol createSymbol(Class<? extends Symbol> symbolType) {
		Symbol symbol = new ConnectorSymbol();
		try {
			symbol = (Symbol)Thread.currentThread().getContextClassLoader().loadClass(symbolType.getName()).newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		symbol.setName("커낵터");
		return fillSymbol(symbol);
	}
	
	private static Symbol fillSymbol (Symbol symbol){
		
		symbol.setShapeId(SHAPE_ID);
		symbol.setHeight(150);
		symbol.setWidth(200);
		symbol.setElementClassName(AssociationTransition.class.getName());
		symbol.setShapeType("EDGE");
		
		return symbol;
	}
}
