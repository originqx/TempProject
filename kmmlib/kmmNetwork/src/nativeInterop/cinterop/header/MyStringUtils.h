//
//  MyStringUtils.h
//  IOSDemoApp
//
//  Created by ZYB on 2024/10/30.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface MyStringUtils : NSObject

// 判断字符串是否为 null 或空字符串
+ (BOOL)isStringNullOrEmpty:(NSString * _Nullable)string;

@end

NS_ASSUME_NONNULL_END
