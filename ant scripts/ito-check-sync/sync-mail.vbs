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
      .To = rcpt                      'mailitem���ռ�������
      .CC = ccpt                      'mailitem�ĳ���������
      .Importance = olImportanceHigh  '���ʼ�����Ҫ������Ϊ��
      .subject = subject              'mailitem����������
      .Body =body 			     	  'mailitem����������
      
      '�����Ҫ�����������͸��ʼ����ϸ���
      If Len(attachfile) <> 0 Then .Attachments.Add attachfile   
      .Send                           '����
    End With

  Set newmail = nothing
  Set olapp =nothing

End Sub