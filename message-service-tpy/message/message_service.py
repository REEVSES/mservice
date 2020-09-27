#coding: utf-8
from message.api import MessageService
#引入thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol
from thrift.server import TServer
#引入smtp和mime
import smtplib
from email.mime.text import MIMEText
from email.header import Header

sender='liuyaozhenshuai@126.com'
authCode='ADNFFXXEWQYIBYCA'

class MessageServiceHandler:
     def sendMobileMessage(self, mobile, message):
         print("sendMobileMessage, mobileNo:"+mobile+" , msg:"+message)
         return True

     # def sendEmailMessage(self, email, message):
     #     print("sendEmailMessage, emailAddr:"+email+", msg:"+message)
     #     messageObj = MIMEText(message, "plain", "utf-8")
     #     messageObj['From'] = sender
     #     messageObj['To'] = email
     #     messageObj['Subject'] = Header('测试主题', 'utf-8')
     #     try:
     #        smtpObj = smtplib.SMTP('smtp.126.com')
     #        smtpObj.login(sender,authCode)
     #        smtpObj.sendmail(sender,[email],messageObj.as_string())
     #     except smtplib.SMTPException as result:
     #         print("send mail fail, msg:"+result)
     #         return False
     #     print("send mail success")
     #     return True

if __name__ == '__main__':
    #消息服务实现者
    handler = MessageServiceHandler()
    processor = MessageService.Processor(handler)
    #监听端口,务必IP形式
    # transport = TSocket.TServerSocket("127.0.0.1", "9090")
    transport = TSocket.TServerSocket("message-service", "9090")
    #传输方式：帧传输
    tfactory = TTransport.TFramedTransportFactory()
    #传输协议：二进制
    pfactory = TBinaryProtocol.TBinaryProtocolFactory()

    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)
    print("py thrift server start")
    server.serve()
    print("py thrift server end")
