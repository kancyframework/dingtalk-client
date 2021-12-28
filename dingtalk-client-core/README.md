## 钉钉机器人（dingtalk client core）

### 使用案例

[DingTalkClientTests](./src/test/java/com/github/kancyframework/dingtalk/DingTalkClientTests.java)

### 消息类型

钉钉机器人支持5中消息类型，分别是：普通文本、Markdown富文本、Link、ActionCard、FeedCard

#### Text
![](https://images.cnblogs.com/cnblogs_com/kancy/2069805/o_211228103832_msgtype_text.png)


请求发送Text消息报文：
- `isAtAll=true`时`atMobiles`和`atUserIds`不生效

```json
{
   "msgtype" : "text",
   "text" : {
      "content" : "简单测试"
   },
   "at" : {
      "isAtAll" : false,
      "atMobiles" : ["18079630001","18079630001"],
      "atUserIds" : []
   }
}
```

响应报文：
```json
{
   "errcode" : 0,
   "errmsg" : "ok"
}
```

#### Markdown
![](https://images.cnblogs.com/cnblogs_com/kancy/2069805/o_211228103826_msgtype_markdown.png)

请求发送Markdown消息报文：
- markdown文本中`@18079637336`会自动转换成`@某某人`

```json
{
   "msgtype" : "markdown",
   "markdown" : {
      "title" : "您有一条消息",
      "text" : "![screenshot](@lADOpwk3K80C0M0FoA) \n ### 李白 \n > 一位唐朝诗人！@18079637336"
   },
   "at" : {
      "isAtAll" : false,
      "atMobiles" : [ "18079637336" ],
      "atUserIds" : []
   }
}
```

#### Link
![](https://images.cnblogs.com/cnblogs_com/kancy/2069805/o_211228103819_msgtype_link.png)

请求发送Link消息报文：

```json
{
   "msgtype" : "link",
   "link" : {
      "text" : "本群与百度成功达成合作关系，今后大家有什么不懂的可以直接百度搜索，不用再群里提问浪费时间啦！",
      "title" : "好消息！好消息！",
      "messageUrl" : "https://www.baidu.com",
      "picUrl" : "http://www.baidu.com/img/bd_logo1.png"
   },
   "at" : {
      "isAtAll" : true,
      "atMobiles" : [],
      "atUserIds" : []
   }
}
```

#### ActionCard
![](https://images.cnblogs.com/cnblogs_com/kancy/2069805/o_211228103807_msgtype_actionCard.png)

请求发送ActionCard消息报文：

```json
{
   "msgtype" : "actionCard",
   "actionCard" : {
      "text" : "![screenshot](@lADOpwk3K80C0M0FoA) \n ### 乔布斯20年前想打造一间苹果咖啡厅，而它正是AppleStore的前身 @18079637336",
      "title" : "您收到一条消息！",
      "hideAvatar" : "0",
      "btnOrientation" : "1",
      "btns" : [
         {
            "title" : "内容不错",
            "actionURL" : "https://www.cnblogs.com/kancy/p/13470386.html"
         },
         {
            "title" : "不感兴趣",
            "actionURL" : "https://www.cnblogs.com/kancy/p/13912443.html"
         }
      ]
   },
   "at" : {
      "isAtAll" : false,
      "atMobiles" : [ "18079637336" ],
      "atUserIds" : []
   }
}
```

#### FeedCard
![](https://images.cnblogs.com/cnblogs_com/kancy/2069805/o_211228103813_msgtype_feedCard.png)

请求发送FeedCard消息报文：

```json
{
   "msgtype" : "feedCard",
   "feedCard" : {
      "links" : [
         {
            "title" : "定位占用CPU较高的进程、线程、代码位置？",
            "messageURL" : "https://www.cnblogs.com/kancy/p/13470386.html",
            "picURL" : "https://img1.baidu.com/it/u=3312920655,3266355600&fm=26&fmt=auto"
         },
         {
            "title" : "浅谈我对DDD领域驱动设计的理解",
            "messageURL" : "https://www.cnblogs.com/kancy/p/13425737.html"
         },
         {
            "title" : "单元测试之PowerMock",
            "messageURL" : "https://www.cnblogs.com/kancy/p/13912443.html"
         },
         {
            "title" : "正确创建索引和索引失效",
            "messageURL" : "https://www.cnblogs.com/kancy/p/13460140.html"
         }
      ]
   },
   "at" : {
      "isAtAll" : true,
      "atMobiles" : [],
      "atUserIds" : []
   }
}
```




