# Paper summaries

Paper summary for `Decentralized Access Control with Anonymous Auhthentication of Data
Stored in Clouds`

## Abstract

> A new decentralized access control scheme for secure data storage in clouds that supports 
anonymous authentication. 

## Page 1

The problem in cloud storage

> In one hand, the user should authenticate itself before initiating any transaction, and on the other hand, it must be ensured that the cloud does not tamper with the data that is outsourced

> the cloud or other users do not know the identity of the user

> In server colluding attack, the adversary can compromise storage servers, so that it can modify data files as long as they are internally consistent. To provide secure data storage, the data needs to be encrypted

## Page 2

- the start of the example problem 
- Access control 

Three types of access control

- UBAC user based access control
- RBAC role based access control
- ABAC attribute based access control
> The eXtensible access control markup language [17] has been proposed for ABAC in clouds [18].

> a new protocol known as attribute-based signature (ABS) has been applied, ABS can be combined with ABE to achieve authenticated 
access control without disclosing the identity of the user to the cloud

## Page 3

> Unlike [24], our scheme is resistant to replay attacks, in which a user can replace fresh data with stale data from a previous write, even if it no longer has valid claim policy

## Page 4

- ABE

e... all the math equations ... wtf

## Page 5

－ ABS 

## Page 6

> A user Uu first registers itself with one or more trustees
> the trustee give the user token
> The user also constructs a claim policy Y to enable the cloud to authenticate the user. The creator does not send the message MSG as is, but uses the time stamp 􏰍 and creates HðCÞk􏰍. This is done to prevent replay attacks. 

## Page 7 

realife example 

## Page 8 - 10

ba la ba la 

## Pre

- 数据存储在`云｀中
- 数据需要加密
- 数据会有经常的 CRUD 
- 数据需要搜索(不过就不能模糊搜索了)

Vocabulary

revocation 取消
outsource 外包,外购
prone 易于, 有倾向
adversary 对手
compromise 危害
disclosed 公开
stale 陈腐的
monotone 单调
prime 质数
