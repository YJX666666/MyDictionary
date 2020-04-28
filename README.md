## 小词典

2020年4月19日开始开发，旨在锻炼自身开发能力、以及版本管理工具的使用。

### 目标功能：

##### 添加单词：

- 输入框单行输入
- 点击添加单词按钮实现添加功能
- 添加前对单词输入框和中文输入框进行判断，如果为空则提示
- 添加成功提示

##### 词库管理：

- 列表展示词库所有单词以及翻译
- 长按任意列弹出对话框，对话框显示当前选择单词以及删除按钮和修改按钮，点击删除按钮删除词库中当前单词，点击修改弹出新对话框，实现更改功能

##### 选择题模式：

- 选择题模式分两种模式：给出中文选英文&给出英文选中文
- 四个按钮需满足其中一个为正确答案，其余三个为随机非正确答案
- 选择正确时，当前选项变为绿色
- 选择错误时，当前选项变为红色，且提示出正确答案
- 每次选择完（无论对错），三个非正确答案都显示其对应的翻译（或者单词）
- 

- 当前BUG：总是重复出现某几组单词

##### 填空题模式：

- 填空题模式同样分为两种：给出中文填写英文&给出英文填写中文
- 清空按钮，点击清空输入框中内容
- 填写正确时，填写的内容变为绿色
- 填写错误时，填写的内容变为红色，且提示出正确答案

