//
//  Generated by the J2ObjC translator.  DO NOT EDIT!
//  source: D:\Java\git\core-ios-widgets\IOSNavigationDrawerPlugin\src\main\java\com\ashera\drawerlayout\DrawerLayoutImpl.java
//

#include "J2ObjC_header.h"

#pragma push_macro("INCLUDE_ALL_DrawerLayoutImpl")
#ifdef RESTRICT_DrawerLayoutImpl
#define INCLUDE_ALL_DrawerLayoutImpl 0
#else
#define INCLUDE_ALL_DrawerLayoutImpl 1
#endif
#undef RESTRICT_DrawerLayoutImpl

#if !defined (ASDrawerLayoutImpl_) && (INCLUDE_ALL_DrawerLayoutImpl || defined(INCLUDE_ASDrawerLayoutImpl))
#define ASDrawerLayoutImpl_

#define RESTRICT_BaseHasWidgets 1
#define INCLUDE_ASBaseHasWidgets 1
#include "BaseHasWidgets.h"

@class ASWidgetAttribute;
@class IOSClass;
@protocol ASIFragment;
@protocol ASILifeCycleDecorator;
@protocol ASIWidget;
@protocol JavaUtilMap;

@interface ASDrawerLayoutImpl : ASBaseHasWidgets
@property id uiView;

#pragma mark Public

- (instancetype)init;

- (instancetype)initWithNSString:(NSString *)localname;

- (instancetype)initWithNSString:(NSString *)groupName
                    withNSString:(NSString *)localname;

- (void)addWithASIWidget:(id<ASIWidget>)w
                 withInt:(jint)index;

- (id)asNativeWidget;

- (id)asWidget;

- (jboolean)checkIosVersionWithNSString:(NSString *)v;

- (void)createWithASIFragment:(id<ASIFragment>)fragment
              withJavaUtilMap:(id<JavaUtilMap>)params;

- (id)getAttributeWithASWidgetAttribute:(ASWidgetAttribute *)key
              withASILifeCycleDecorator:(id<ASILifeCycleDecorator>)decorator;

- (id)getChildAttributeWithASIWidget:(id<ASIWidget>)w
               withASWidgetAttribute:(ASWidgetAttribute *)key;

- (IOSClass *)getViewClass;

- (void)initialized OBJC_METHOD_FAMILY_NONE;

- (void)invalidate;

- (void)loadAttributesWithNSString:(NSString *)localName;

- (void)nativeCreateWithJavaUtilMap:(id<JavaUtilMap>)params;

- (void)nativeMakeFrameForChildWidgetWithInt:(jint)l
                                     withInt:(jint)t
                                     withInt:(jint)r
                                     withInt:(jint)b;

- (id<ASIWidget>)newInstance OBJC_METHOD_FAMILY_NONE;

- (jboolean)removeWithInt:(jint)index;

- (jboolean)removeWithASIWidget:(id<ASIWidget>)w;

- (void)requestLayout;

- (void)setAttributeWithASWidgetAttribute:(ASWidgetAttribute *)key
                             withNSString:(NSString *)strValue
                                   withId:(id)objValue
                withASILifeCycleDecorator:(id<ASILifeCycleDecorator>)decorator;

- (void)setChildAttributeWithASIWidget:(id<ASIWidget>)w
                 withASWidgetAttribute:(ASWidgetAttribute *)key
                          withNSString:(NSString *)strValue
                                withId:(id)objValue;

- (void)setIdWithNSString:(NSString *)id_;

- (void)setVisibleWithBoolean:(jboolean)b;

#pragma mark Package-Private

@end

J2OBJC_EMPTY_STATIC_INIT(ASDrawerLayoutImpl)

inline NSString *ASDrawerLayoutImpl_get_LOCAL_NAME(void);
/*! INTERNAL ONLY - Use accessor function from above. */
FOUNDATION_EXPORT NSString *ASDrawerLayoutImpl_LOCAL_NAME;
J2OBJC_STATIC_FIELD_OBJ_FINAL(ASDrawerLayoutImpl, LOCAL_NAME, NSString *)

inline NSString *ASDrawerLayoutImpl_get_GROUP_NAME(void);
/*! INTERNAL ONLY - Use accessor function from above. */
FOUNDATION_EXPORT NSString *ASDrawerLayoutImpl_GROUP_NAME;
J2OBJC_STATIC_FIELD_OBJ_FINAL(ASDrawerLayoutImpl, GROUP_NAME, NSString *)

FOUNDATION_EXPORT void ASDrawerLayoutImpl_init(ASDrawerLayoutImpl *self);

FOUNDATION_EXPORT ASDrawerLayoutImpl *new_ASDrawerLayoutImpl_init(void) NS_RETURNS_RETAINED;

FOUNDATION_EXPORT ASDrawerLayoutImpl *create_ASDrawerLayoutImpl_init(void);

FOUNDATION_EXPORT void ASDrawerLayoutImpl_initWithNSString_(ASDrawerLayoutImpl *self, NSString *localname);

FOUNDATION_EXPORT ASDrawerLayoutImpl *new_ASDrawerLayoutImpl_initWithNSString_(NSString *localname) NS_RETURNS_RETAINED;

FOUNDATION_EXPORT ASDrawerLayoutImpl *create_ASDrawerLayoutImpl_initWithNSString_(NSString *localname);

FOUNDATION_EXPORT void ASDrawerLayoutImpl_initWithNSString_withNSString_(ASDrawerLayoutImpl *self, NSString *groupName, NSString *localname);

FOUNDATION_EXPORT ASDrawerLayoutImpl *new_ASDrawerLayoutImpl_initWithNSString_withNSString_(NSString *groupName, NSString *localname) NS_RETURNS_RETAINED;

FOUNDATION_EXPORT ASDrawerLayoutImpl *create_ASDrawerLayoutImpl_initWithNSString_withNSString_(NSString *groupName, NSString *localname);

J2OBJC_TYPE_LITERAL_HEADER(ASDrawerLayoutImpl)

@compatibility_alias ComAsheraDrawerlayoutDrawerLayoutImpl ASDrawerLayoutImpl;

#endif

#if !defined (ASDrawerLayoutImpl_DrawerLockMode_) && (INCLUDE_ALL_DrawerLayoutImpl || defined(INCLUDE_ASDrawerLayoutImpl_DrawerLockMode))
#define ASDrawerLayoutImpl_DrawerLockMode_

#define RESTRICT_AbstractEnumToIntConverter 1
#define INCLUDE_ASAbstractEnumToIntConverter 1
#include "AbstractEnumToIntConverter.h"

@class JavaLangInteger;
@protocol JavaUtilMap;

@interface ASDrawerLayoutImpl_DrawerLockMode : ASAbstractEnumToIntConverter

#pragma mark Public

- (JavaLangInteger *)getDefault;

- (id<JavaUtilMap>)getMapping;

#pragma mark Package-Private

- (instancetype)init;

@end

J2OBJC_EMPTY_STATIC_INIT(ASDrawerLayoutImpl_DrawerLockMode)

FOUNDATION_EXPORT void ASDrawerLayoutImpl_DrawerLockMode_init(ASDrawerLayoutImpl_DrawerLockMode *self);

FOUNDATION_EXPORT ASDrawerLayoutImpl_DrawerLockMode *new_ASDrawerLayoutImpl_DrawerLockMode_init(void) NS_RETURNS_RETAINED;

FOUNDATION_EXPORT ASDrawerLayoutImpl_DrawerLockMode *create_ASDrawerLayoutImpl_DrawerLockMode_init(void);

J2OBJC_TYPE_LITERAL_HEADER(ASDrawerLayoutImpl_DrawerLockMode)

#endif

#if !defined (ASDrawerLayoutImpl_DrawerLayoutExt_) && (INCLUDE_ALL_DrawerLayoutImpl || defined(INCLUDE_ASDrawerLayoutImpl_DrawerLayoutExt))
#define ASDrawerLayoutImpl_DrawerLayoutExt_

#define RESTRICT_DrawerLayout 1
#define INCLUDE_ADXDrawerLayout 1
#include "DrawerLayout.h"

#define RESTRICT_ILifeCycleDecorator 1
#define INCLUDE_ASILifeCycleDecorator 1
#include "ILifeCycleDecorator.h"

#define RESTRICT_IMaxDimension 1
#define INCLUDE_ASIMaxDimension 1
#include "IMaxDimension.h"

@class ADRect;
@class ADView;
@class ASDrawerLayoutImpl;
@class ASWidgetAttribute;
@class IOSIntArray;
@class IOSObjectArray;
@protocol ASIWidget;
@protocol JavaUtilList;

@interface ASDrawerLayoutImpl_DrawerLayoutExt : ADXDrawerLayout < ASILifeCycleDecorator, ASIMaxDimension >

#pragma mark Public

- (instancetype)initWithASDrawerLayoutImpl:(ASDrawerLayoutImpl *)outer$;

- (void)drawableStateChanged;

- (void)endViewTransitionWithADView:(ADView *)view;

- (void)executeWithNSString:(NSString *)method
          withNSObjectArray:(IOSObjectArray *)canvas;

- (id)getAttributeWithASWidgetAttribute:(ASWidgetAttribute *)widgetAttribute;

- (void)getLocationOnScreenWithIntArray:(IOSIntArray *)appScreenLocation;

- (jint)getMaxHeight;

- (jint)getMaxWidth;

- (id<JavaUtilList>)getMethods;

- (id<ASIWidget>)getWidget;

- (void)getWindowVisibleDisplayFrameWithADRect:(ADRect *)displayFrame;

- (ADView *)inflateViewWithNSString:(NSString *)layout;

- (void)initialized OBJC_METHOD_FAMILY_NONE;

- (id<ASILifeCycleDecorator>)newInstanceWithASIWidget:(id<ASIWidget>)widget OBJC_METHOD_FAMILY_NONE;

- (void)offsetLeftAndRightWithInt:(jint)offset;

- (void)offsetTopAndBottomWithInt:(jint)offset;

- (void)onMeasureWithInt:(jint)widthMeasureSpec
                 withInt:(jint)heightMeasureSpec;

- (void)remeasure;

- (void)removeFromParent;

- (void)setAttributeWithASWidgetAttribute:(ASWidgetAttribute *)widgetAttribute
                             withNSString:(NSString *)strValue
                                   withId:(id)objValue;

- (void)setMaxHeightWithInt:(jint)height;

- (void)setMaxWidthWithInt:(jint)width;

- (void)setMyAttributeWithNSString:(NSString *)name
                            withId:(id)value;

- (void)setState0WithId:(id)value;

- (void)setState1WithId:(id)value;

- (void)setState2WithId:(id)value;

- (void)setState3WithId:(id)value;

- (void)setState4WithId:(id)value;

- (void)setVisibilityWithInt:(jint)visibility;

- (void)smoothSlideViewToWithADView:(ADView *)drawerView
                            withInt:(jint)x
                            withInt:(jint)y;

- (void)state0;

- (void)state1;

- (void)state2;

- (void)state3;

- (void)state4;

- (void)stateNo;

- (void)stateYes;

- (void)updateMeasuredDimensionWithInt:(jint)width
                               withInt:(jint)height;

#pragma mark Protected

- (void)onLayoutWithBoolean:(jboolean)changed
                    withInt:(jint)l
                    withInt:(jint)t
                    withInt:(jint)r
                    withInt:(jint)b;

// Disallowed inherited constructors, do not use.

- (instancetype)init NS_UNAVAILABLE;

@end

J2OBJC_EMPTY_STATIC_INIT(ASDrawerLayoutImpl_DrawerLayoutExt)

FOUNDATION_EXPORT void ASDrawerLayoutImpl_DrawerLayoutExt_initWithASDrawerLayoutImpl_(ASDrawerLayoutImpl_DrawerLayoutExt *self, ASDrawerLayoutImpl *outer$);

FOUNDATION_EXPORT ASDrawerLayoutImpl_DrawerLayoutExt *new_ASDrawerLayoutImpl_DrawerLayoutExt_initWithASDrawerLayoutImpl_(ASDrawerLayoutImpl *outer$) NS_RETURNS_RETAINED;

FOUNDATION_EXPORT ASDrawerLayoutImpl_DrawerLayoutExt *create_ASDrawerLayoutImpl_DrawerLayoutExt_initWithASDrawerLayoutImpl_(ASDrawerLayoutImpl *outer$);

J2OBJC_TYPE_LITERAL_HEADER(ASDrawerLayoutImpl_DrawerLayoutExt)

#endif

#pragma pop_macro("INCLUDE_ALL_DrawerLayoutImpl")
