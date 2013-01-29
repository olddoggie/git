Dim fso,f,body,rcpt,ccpt,subject,needattachment,attachfile
rcpt=WScript.Arguments(0)
'ccpt=WScript.Arguments(1)
subject=WScript.Arguments(1)
openfile=WScript.Arguments(2)
needattachment=WScript.Arguments(3)
If needattachment="true" Then
attachfile=""
Else
attachfile="D:\cygwin\home\jack.wang\cron.log"
End if

Set fso = CreateObject("Scripting.FileSystemObject")
Const ForReading = 1, ForWriting = 2, ForAppending = 8
Set f=fso.OpenTextFile(openfile,1,true)
'Do Until f.AtEndOfStream 
'body=body & f.ReadLine & vbCRLf
'Loop
body = f.ReadAll
set fso =nothing
Call sendmailByOutlook(rcpt, subject, body, attachment)

Sub SendmailByOutlook(rcpt, subject, body, attachment)
 
  Set olapp = CreateObject("outlook.application")
  Set newmail = olapp.CreateItem(olMailItem)
    With newmail
      .To = rcpt                      'mailitem的收件人属性
      .CC = ccpt                      'mailitem的抄送人属性
      .Importance = olImportanceHigh  '将邮件的重要性设置为高
      .subject = subject              'mailitem的主题属性
      .Body =body 			     	  'mailitem的正文属性
      
      '如果需要包含附件，就给邮件添上附件
      If Len(attachfile) <> 0 Then .Attachments.Add attachfile   
      .Send                           '发送
    End With

  Set newmail = nothing
  Set olapp =nothing

End Sub