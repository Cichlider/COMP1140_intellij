# 事件处理系统 (Event Handling System)

## 题目概述

这是一个Java编程题，要求实现一个事件处理系统，包含三个核心类：`Event`、`EventHandler` 和 `EventsContainer`。

## 类的详细说明

### 1. Event.java (已完成)
**作用**: 表示一个要被事件处理函数处理的事件

**特点**:
- 使用Java 14+ 的 `record` 关键字定义
- 包含两个字段：`kind`（事件类型）和 `data`（事件数据）
- 事件类型是**大小写敏感**的（如 "KEY_PRESSED" 和 "key_pressed" 是不同的事件类型）
- 一旦创建，事件的 `kind` 和 `data` 不能被修改（不可变性）

**示例**:
```java
Event keyEvent = new Event("KEY_PRESSED", "F10");
Event mouseEvent = new Event("MOUSE_LEFT_CLICK", "X:10 Y:20");
```

### 2. EventHandler.java (需要实现)
**作用**: 事件分发器，负责将不同类型的事件分发给对应的处理函数

**需要实现的方法**:

#### `registerEventHandlerFunction(String eventKind, Consumer<Event> handlerFunction)`
- **功能**: 为特定类型的事件注册处理函数
- **参数**: 
  - `eventKind`: 事件类型名称
  - `handlerFunction`: 处理该类型事件的函数（Consumer<Event>类型）
- **行为**: 如果该事件类型已有处理函数，则替换为新的处理函数

#### `unregisterEventHandlerFunction(String eventKind)`
- **功能**: 取消注册特定类型事件的处理函数
- **参数**: `eventKind`: 要取消注册的事件类型
- **返回值**: 返回之前注册的处理函数，如果没有则返回 `null`

#### `handleEvent(Event event)`
- **功能**: 处理给定的事件
- **参数**: `event`: 要处理的事件
- **返回值**: 如果找到对应的处理函数并成功调用则返回 `true`，否则返回 `false`
- **行为**: 根据事件的 `kind` 查找对应的处理函数，如果找到则调用该函数处理事件

**实现提示**:
- 需要使用 `Map<String, Consumer<Event>>` 来存储事件类型与处理函数的映射关系

### 3. EventsContainer.java (需要实现)
**作用**: 有序的事件容器，用于存储待处理的事件

**特点**:
- 事件按照添加的顺序保存（先进先出，FIFO）
- 容器本身不处理事件，依赖 `EventHandler` 来分发事件

**需要实现的方法**:

#### `addEvent(Event event)`
- **功能**: 在容器末尾添加新事件
- **参数**: `event`: 要添加的事件

#### `extractEvent()`
- **功能**: 从容器头部提取（移除）事件
- **返回值**: 容器头部的事件，如果容器为空则返回 `null`

#### `handleEvents(EventHandler eventHandler)`
- **功能**: 使用给定的事件处理器尽可能多地处理容器中的事件
- **参数**: `eventHandler`: 事件处理器
- **返回值**: 返回已处理事件的列表
- **行为**: 
  - 按添加顺序遍历容器中的所有事件
  - 对每个事件调用 `eventHandler.handleEvent()`
  - 如果事件能被处理，从容器中移除该事件
  - 如果事件不能被处理，保留在容器中，维持原有顺序
  - 返回所有被成功处理的事件列表

**实现提示**:
- 可以使用 `LinkedList<Event>` 或 `ArrayList<Event>` 来存储事件
- `LinkedList` 在头部删除操作上效率更高

## 设计思路

### EventHandler 实现思路:
```java
public class EventHandler {
    private Map<String, Consumer<Event>> handlers = new HashMap<>();
    
    public void registerEventHandlerFunction(String eventKind, Consumer<Event> handlerFunction) {
        handlers.put(eventKind, handlerFunction);
    }
    
    public Consumer<Event> unregisterEventHandlerFunction(String eventKind) {
        return handlers.remove(eventKind);
    }
    
    public boolean handleEvent(Event event) {
        Consumer<Event> handler = handlers.get(event.kind());
        if (handler != null) {
            handler.accept(event);
            return true;
        }
        return false;
    }
}
```

### EventsContainer 实现思路:
```java
public class EventsContainer {
    private List<Event> events = new LinkedList<>();
    
    public void addEvent(Event event) {
        events.add(event);
    }
    
    public Event extractEvent() {
        if (events.isEmpty()) {
            return null;
        }
        return events.remove(0);
    }
    
    public List<Event> handleEvents(EventHandler eventHandler) {
        List<Event> handledEvents = new ArrayList<>();
        Iterator<Event> iterator = events.iterator();
        
        while (iterator.hasNext()) {
            Event event = iterator.next();
            if (eventHandler.handleEvent(event)) {
                handledEvents.add(event);
                iterator.remove();
            }
        }
        
        return handledEvents;
    }
}
```

## 使用示例

```java
// 创建事件处理器
EventHandler handler = new EventHandler();

// 注册事件处理函数
handler.registerEventHandlerFunction("KEY_PRESSED", 
    event -> System.out.println("Key pressed: " + event.data()));
handler.registerEventHandlerFunction("MOUSE_CLICK", 
    event -> System.out.println("Mouse clicked at: " + event.data()));

// 创建事件容器
EventsContainer container = new EventsContainer();

// 添加事件
container.addEvent(new Event("KEY_PRESSED", "F10"));
container.addEvent(new Event("MOUSE_CLICK", "X:100 Y:200"));
container.addEvent(new Event("UNKNOWN_EVENT", "some data"));

// 处理事件
List<Event> handledEvents = container.handleEvents(handler);
System.out.println("Handled " + handledEvents.size() + " events");
```

## 注意事项

1. **不要修改**已有方法的签名
2. 事件类型是**大小写敏感**的
3. 需要正确处理 `null` 值的情况
4. 事件容器需要保持事件的原有顺序
5. 使用合适的数据结构来提高效率 