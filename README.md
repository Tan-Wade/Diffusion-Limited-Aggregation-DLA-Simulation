# Diffusion-Limited Aggregation (DLA) Simulation

一个使用Java实现的扩散限制聚合(Diffusion Limited Aggregation, DLA)模拟项目，用于研究分形结构和复杂系统的生长过程。

## 项目简介

扩散限制聚合(DLA)是一个重要的物理和数学模型，用于模拟各种自然现象，如：
- 电沉积过程
- 雪花形成
- 矿物沉积
- 细菌菌落生长
- 城市发展模式

本项目实现了两种DLA算法变体，并提供了相关的分析和可视化工具。

## 项目结构

```
.
├── AggregationDynamic.java    # 动态半径调整的DLA模拟（带陷阱机制）
├── AggregationTraps.java      # 固定半径的DLA模拟（带陷阱机制）
├── Corr.java                  # 关联函数计算工具（用于计算分形维数）
├── RandomWalks.java           # 随机游走模拟
├── Sierp.java                 # 谢尔宾斯基三角分形生成器
├── SierpTest.java             # Sierp类的测试/示例程序
├── StdDraw.java               # 图形绘制库（来自Princeton）
└── README.md                  # 本文件
```

## 功能特性

### 1. DLA聚合模拟
- **AggregationDynamic.java**: 实现了动态调整释放半径的DLA算法，释放半径会随着簇的生长自动调整，提高模拟效率
- **AggregationTraps.java**: 实现了固定释放半径的DLA算法，包含随机陷阱机制来模拟障碍物对聚合过程的影响

### 2. 数据分析工具
- **Corr.java**: 计算粒子簇的关联函数(correlation function)，用于分析分形维数

### 3. 辅助工具
- **RandomWalks.java**: 二维随机游走模拟，这是DLA中粒子运动的基础
- **Sierp.java**: 生成谢尔宾斯基三角(Sierpinski gasket)，用于分形对比研究

## 环境要求

- Java JDK 8 或更高版本
- 支持图形界面的操作系统（用于可视化）

## 编译方法

编译所有Java文件：

```bash
javac *.java
```

或者单独编译某个文件：

```bash
javac AggregationDynamic.java
javac AggregationTraps.java
javac Corr.java
javac RandomWalks.java
javac Sierp.java
javac StdDraw.java
```

## 运行方法

### 1. 运行动态半径DLA模拟

```bash
java AggregationDynamic
```

程序会提示输入以下参数：
- `L`: 晶格大小（建议值：100-200）
- `N`: 粒子数量（建议值：500-2000）
- `pTr`: 陷阱概率（0.0-1.0之间）
- `fact`: 动态半径调整因子（建议值：1.5-2.5）
- `seed1`: 随机数种子1（用于陷阱生成）
- `seed2`: 随机数种子2（用于粒子运动）

**示例输入**：
```
Give size of lattice L: 100
Give number of particles N: 500
Give the probability of traps pTr: 0.01
Give the multiplicative factor for dyamic R adjustment, fact: 2.0
Give seed1: 362459
Give seed2: 534387
```

### 2. 运行固定半径DLA模拟

```bash
java AggregationTraps
```

参数说明：
- `L`: 晶格大小
- `N`: 粒子数量
- `pTr`: 陷阱概率
- `seed1`: 随机数种子1
- `seed2`: 随机数种子2

### 3. 计算关联函数和分形维数

首先运行DLA模拟生成`f.dat`文件，然后：

```bash
java Corr
```

输入参数：
- `N`: 簇中的粒子数量（与DLA模拟中的N相同）
- `M`: 直方图分箱数量（建议值：50-100）
- `Rmin`: 最小距离
- `Rmax`: 最大距离

程序会输出：
- 分形维数 `D = 1 + beta`
- 关联函数数据文件 `h.dat`

### 4. 运行随机游走模拟

```bash
java RandomWalks
```

输入参数：
- `L`: 场大小
- `N`: 步数
- `seed1`: 随机数种子1
- `seed2`: 随机数种子2

### 5. 生成谢尔宾斯基三角

```bash
javac Sierp.java SierpTest.java
java SierpTest
```

这会生成一个彩色的谢尔宾斯基三角分形。你可以在`SierpTest.java`中修改`stopSize`参数来控制细节层次（值越小，细节越多）。

## 输出说明

- **f.dat**: 包含聚合簇中所有粒子的坐标(x, y)
- **h.dat**: 关联函数的直方图数据，可用于绘制关联函数图

## 可视化

所有模拟程序都使用`StdDraw`库进行实时可视化：
- 红色圆点表示已聚合的粒子
- 程序运行时会实时显示聚合过程
- 可以通过窗口菜单保存图像

## 科学背景

### DLA算法原理

1. 在二维格点上放置一个种子粒子
2. 从远离种子粒子的边界上随机释放一个新粒子
3. 新粒子执行随机游走
4. 当粒子接触到已聚合的粒子时，附着在簇上
5. 重复步骤2-4，直到达到指定的粒子数量

### 分形维数

DLA簇具有分形结构，其分形维数约为1.7（二维情况下）。本项目通过关联函数分析来计算分形维数。

## 使用建议

- 开始可以使用较小的参数值（如L=50, N=200）来快速查看效果
- 生成美观的分形结构可以使用较大的N值（如N=1000-5000）
- 陷阱概率pTr建议在0.0-0.05之间，过高会导致粒子无法形成簇
- 动态半径因子fact建议在1.5-2.5之间

## 参考文献

- Witten, T. A., & Sander, L. M. (1981). Diffusion-limited aggregation, a kinetic critical phenomenon. *Physical Review Letters*, 47(19), 1400.
- Mandelbrot, B. B. (1982). *The fractal geometry of nature*. WH Freeman.

## 作者

Zeyuan - Summer Project

## 许可证

本项目仅供学习和研究使用。

