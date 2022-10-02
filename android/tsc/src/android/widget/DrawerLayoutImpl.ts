// start - imports

export const enum DrawerLockMode {
LOCK_MODE_UNLOCKED = "LOCK_MODE_UNLOCKED",
LOCK_MODE_LOCKED_CLOSED = "LOCK_MODE_LOCKED_CLOSED",
LOCK_MODE_LOCKED_OPEN = "LOCK_MODE_LOCKED_OPEN",
LOCK_MODE_UNDEFINED = "LOCK_MODE_UNDEFINED",
}	
import CommandAttr from '../../widget/CommandAttr';
import IWidget from '../../widget/IWidget';
import ILayoutParam from '../../widget/ILayoutParam';
import {plainToClass, Type, Exclude, Expose, Transform} from "class-transformer";
import 'babel-polyfill';
import {Gravity} from '../../widget/TypeConstants';
import {ITranform, TransformerFactory} from '../../widget/TransformerFactory';
import {Event} from '../../app/Event';
import {MotionEvent} from '../../app/MotionEvent';
import {DragEvent} from '../../app/DragEvent';
import {KeyEvent} from '../../app/KeyEvent';
import { ScopedObject } from '../../app/ScopedObject';















import {ViewGroupImpl_LayoutParams} from './ViewGroupImpl';

// end - imports
import {ViewGroupImpl} from './ViewGroupImpl';
export abstract class DrawerLayoutImpl<T> extends ViewGroupImpl<T>{
	//start - body
	static initialize() {
    }	
	@Type(() => CommandAttr)
	@Expose({ name: "elevation" })
	elevation!:CommandAttr<string>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "scrimColor" })
	scrimColor!:CommandAttr<string>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "drawerLockMode" })
	drawerLockMode!:CommandAttr<DrawerLockMode>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "statusBarBackground" })
	statusBarBackground!:CommandAttr<string>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "onDrawerSlide" })
	onDrawerSlide!:CommandAttr<string>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "onDrawerOpened" })
	onDrawerOpened!:CommandAttr<string>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "onDrawerClosed" })
	onDrawerClosed!:CommandAttr<string>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "onDrawerStateChange" })
	onDrawerStateChange!:CommandAttr<string>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "drawerGravity" })
	drawerGravity!:CommandAttr<Gravity[]>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "openDrawer" })
	openDrawer_!:CommandAttr<Gravity[]>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "closeDrawer" })
	closeDrawer_!:CommandAttr<Gravity[]>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "isOpenStart" })
	isOpenStart_!:CommandAttr<boolean>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "isOpenEnd" })
	isOpenEnd_!:CommandAttr<boolean>| undefined;
	@Type(() => CommandAttr)
	@Expose({ name: "edgeSize" })
	edgeSize_!:CommandAttr<string>| undefined;

	@Exclude()
	protected thisPointer: T;	
	protected abstract getThisPointer(): T;
	reset() : T {	
		super.reset();
		this.elevation = undefined;
		this.scrimColor = undefined;
		this.drawerLockMode = undefined;
		this.statusBarBackground = undefined;
		this.onDrawerSlide = undefined;
		this.onDrawerOpened = undefined;
		this.onDrawerClosed = undefined;
		this.onDrawerStateChange = undefined;
		this.drawerGravity = undefined;
		this.openDrawer_ = undefined;
		this.closeDrawer_ = undefined;
		this.isOpenStart_ = undefined;
		this.isOpenEnd_ = undefined;
		this.edgeSize_ = undefined;
		return this.thisPointer;
	}
	constructor(id: string, path: string[], event:  string) {
		super(id, path, event);
		this.thisPointer = this.getThisPointer();
	}
	

	public tryGetElevation() : T {
		this.resetIfRequired();
		if (this.elevation == null || this.elevation == undefined) {
			this.elevation = new CommandAttr<string>()
		}
		
		this.elevation.setGetter(true);
		this.orderGet++;
		this.elevation.setOrderGet(this.orderGet);
		return this.thisPointer;
	}
	
	public getElevation() : string {
		if (this.elevation == null || this.elevation == undefined) {
			this.elevation = new CommandAttr<string>();
		}
		return this.elevation.getCommandReturnValue();
	}
	public setElevation(value : string) : T {
		this.resetIfRequired();
		if (this.elevation == null || this.elevation == undefined) {
			this.elevation = new CommandAttr<string>();
		}
		
		this.elevation.setSetter(true);
		this.elevation.setValue(value);
		this.orderSet++;
		this.elevation.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
		

	public setScrimColor(value : string) : T {
		this.resetIfRequired();
		if (this.scrimColor == null || this.scrimColor == undefined) {
			this.scrimColor = new CommandAttr<string>();
		}
		
		this.scrimColor.setSetter(true);
		this.scrimColor.setValue(value);
		this.orderSet++;
		this.scrimColor.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
		

	public setDrawerLockMode(value : DrawerLockMode) : T {
		this.resetIfRequired();
		if (this.drawerLockMode == null || this.drawerLockMode == undefined) {
			this.drawerLockMode = new CommandAttr<DrawerLockMode>();
		}
		
		this.drawerLockMode.setSetter(true);
		this.drawerLockMode.setValue(value);
		this.orderSet++;
		this.drawerLockMode.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
		

	public setStatusBarBackground(value : string) : T {
		this.resetIfRequired();
		if (this.statusBarBackground == null || this.statusBarBackground == undefined) {
			this.statusBarBackground = new CommandAttr<string>();
		}
		
		this.statusBarBackground.setSetter(true);
		this.statusBarBackground.setValue(value);
		this.orderSet++;
		this.statusBarBackground.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
		

	public setOnDrawerSlide(value : string) : T {
		this.resetIfRequired();
		if (this.onDrawerSlide == null || this.onDrawerSlide == undefined) {
			this.onDrawerSlide = new CommandAttr<string>();
		}
		
		this.onDrawerSlide.setSetter(true);
		this.onDrawerSlide.setValue(value);
		this.orderSet++;
		this.onDrawerSlide.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
		

	public setOnDrawerOpened(value : string) : T {
		this.resetIfRequired();
		if (this.onDrawerOpened == null || this.onDrawerOpened == undefined) {
			this.onDrawerOpened = new CommandAttr<string>();
		}
		
		this.onDrawerOpened.setSetter(true);
		this.onDrawerOpened.setValue(value);
		this.orderSet++;
		this.onDrawerOpened.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
		

	public setOnDrawerClosed(value : string) : T {
		this.resetIfRequired();
		if (this.onDrawerClosed == null || this.onDrawerClosed == undefined) {
			this.onDrawerClosed = new CommandAttr<string>();
		}
		
		this.onDrawerClosed.setSetter(true);
		this.onDrawerClosed.setValue(value);
		this.orderSet++;
		this.onDrawerClosed.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
		

	public setOnDrawerStateChange(value : string) : T {
		this.resetIfRequired();
		if (this.onDrawerStateChange == null || this.onDrawerStateChange == undefined) {
			this.onDrawerStateChange = new CommandAttr<string>();
		}
		
		this.onDrawerStateChange.setSetter(true);
		this.onDrawerStateChange.setValue(value);
		this.orderSet++;
		this.onDrawerStateChange.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
		

	public setDrawerGravity(...value : Gravity[]) : T {
		this.resetIfRequired();
		if (this.drawerGravity == null || this.drawerGravity == undefined) {
			this.drawerGravity = new CommandAttr<Gravity[]>();
		}
		
		this.drawerGravity.setSetter(true);
		this.drawerGravity.setValue(value);
		this.orderSet++;
		this.drawerGravity.setOrderSet(this.orderSet);
this.drawerGravity.setTransformer('gravity');		return this.thisPointer;
	}
		

	public openDrawer(...value : Gravity[]) : T {
		this.resetIfRequired();
		if (this.openDrawer_ == null || this.openDrawer_ == undefined) {
			this.openDrawer_ = new CommandAttr<Gravity[]>();
		}
		
		this.openDrawer_.setSetter(true);
		this.openDrawer_.setValue(value);
		this.orderSet++;
		this.openDrawer_.setOrderSet(this.orderSet);
this.openDrawer_.setTransformer('gravity');		return this.thisPointer;
	}
		

	public closeDrawer(...value : Gravity[]) : T {
		this.resetIfRequired();
		if (this.closeDrawer_ == null || this.closeDrawer_ == undefined) {
			this.closeDrawer_ = new CommandAttr<Gravity[]>();
		}
		
		this.closeDrawer_.setSetter(true);
		this.closeDrawer_.setValue(value);
		this.orderSet++;
		this.closeDrawer_.setOrderSet(this.orderSet);
this.closeDrawer_.setTransformer('gravity');		return this.thisPointer;
	}
		

	public tryGetIsOpenStart() : T {
		this.resetIfRequired();
		if (this.isOpenStart_ == null || this.isOpenStart_ == undefined) {
			this.isOpenStart_ = new CommandAttr<boolean>()
		}
		
		this.isOpenStart_.setGetter(true);
		this.orderGet++;
		this.isOpenStart_.setOrderGet(this.orderGet);
		return this.thisPointer;
	}
	
	public isIsOpenStart() : boolean {
		if (this.isOpenStart_ == null || this.isOpenStart_ == undefined) {
			this.isOpenStart_ = new CommandAttr<boolean>();
		}
		return this.isOpenStart_.getCommandReturnValue();
	}
	
		

	public tryGetIsOpenEnd() : T {
		this.resetIfRequired();
		if (this.isOpenEnd_ == null || this.isOpenEnd_ == undefined) {
			this.isOpenEnd_ = new CommandAttr<boolean>()
		}
		
		this.isOpenEnd_.setGetter(true);
		this.orderGet++;
		this.isOpenEnd_.setOrderGet(this.orderGet);
		return this.thisPointer;
	}
	
	public isIsOpenEnd() : boolean {
		if (this.isOpenEnd_ == null || this.isOpenEnd_ == undefined) {
			this.isOpenEnd_ = new CommandAttr<boolean>();
		}
		return this.isOpenEnd_.getCommandReturnValue();
	}
	
		

	public edgeSize(value : string) : T {
		this.resetIfRequired();
		if (this.edgeSize_ == null || this.edgeSize_ == undefined) {
			this.edgeSize_ = new CommandAttr<string>();
		}
		
		this.edgeSize_.setSetter(true);
		this.edgeSize_.setValue(value);
		this.orderSet++;
		this.edgeSize_.setOrderSet(this.orderSet);
		return this.thisPointer;
	}
		
	//end - body

}
	
//start - staticinit

export class DrawerLayout extends DrawerLayoutImpl<DrawerLayout> implements IWidget{
    getThisPointer(): DrawerLayout {
        return this;
    }
    
   	public getClass() {
		return DrawerLayout;
	}
	
   	constructor(id: string, path: string[], event: string) {
		super(id, path, event);	
	}
}

DrawerLayoutImpl.initialize();
export interface OnDrawerSlideEvent extends Event{
        //drawerView:View;

	        slideOffset:number;

}
export interface OnDrawerOpenedEvent extends Event{
        //drawerView:View;


}
export interface OnDrawerClosedEvent extends Event{
        //drawerView:View;


}
export interface OnDrawerStateChangedEvent extends Event{
	        newState:number;

}
export interface OnDrawerSlideEvent extends Event{
        //drawerView:View;

	        slideOffset:number;

}
export interface OnDrawerOpenedEvent extends Event{
        //drawerView:View;


}
export interface OnDrawerClosedEvent extends Event{
        //drawerView:View;


}
export interface OnDrawerStateChangedEvent extends Event{
	        newState:number;

}
export interface OnDrawerSlideEvent extends Event{
        //drawerView:View;

	        slideOffset:number;

}
export interface OnDrawerOpenedEvent extends Event{
        //drawerView:View;


}
export interface OnDrawerClosedEvent extends Event{
        //drawerView:View;


}
export interface OnDrawerStateChangedEvent extends Event{
	        newState:number;

}
export interface OnDrawerSlideEvent extends Event{
        //drawerView:View;

	        slideOffset:number;

}
export interface OnDrawerOpenedEvent extends Event{
        //drawerView:View;


}
export interface OnDrawerClosedEvent extends Event{
        //drawerView:View;


}
export interface OnDrawerStateChangedEvent extends Event{
	        newState:number;

}

//end - staticinit
