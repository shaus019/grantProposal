/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  thomasroff
 * Created: 10 Apr. 2020
 */



create table Evaluator (
    staffId varchar(6) primary key,
    username varchar(8) not null,
    password varchar(20) not null,
    firstname varchar(15) not null,
    lastname varchar(15) not null,
    email varchar(30) not null,
    telephone varchar(10),
    department varchar(30) not null
);

create table Grant (
    grantId varchar(10) primary key,
    name varchar(100), 
    amount varchar(10), 
    department varchar(20), 
    dueDate date
);

create table Applicant (
    staffId varchar(6) primary key,
    username varchar(8) not null,
    password varchar(20) not null,
    firstname varchar(15) not null,
    lastname varchar(15) not null,
    email varchar(30) not null,
    telephone varchar(10),
    department varchar(30) not null,
    qualification varchar(15)
);

create table Application (
    applicationId int auto_increment,
    grantId varchar(6) not null,
    applicantId varchar(6) not null,
    date datetime not null,      
    projectTitle varchar(50),
    projectAbstract clob,
    description clob,
    uniqueness clob,
    undertakenWork clob,
    conceptProof clob,
    soldOrUsed clob,
    opinion clob,
    whyBuy clob,
    estimation clob,
    status varchar(30),
-- for evaluation purposes --
--     evaluatorId array
--     comments clob, 

    constraint Application_pk primary key (applicationId),
    constraint Application_Applicant_fk foreign key (applicantId) references Applicant (staffId),
    constraint Application_Grant_fk foreign key (grantID) references Grant,
--     constraint Application_Evaluator_fk foreign key (evaluatorId) references Evaluator(staffId)
    
);

create table DraftApplication (
    draftId varchar(10) auto_increment,
    grantId varchar(6) not null,
    applicantId varchar(6) not null,
    date datetime not null,      
    projectTitle varchar(50),
    projectAbstract clob,
    description clob,
    uniqueness clob,
    undertakenWork clob,
    conceptProof clob,
    soldOrUsed clob,
    opinion clob,
    whyBuy clob,
    estimation clob,
    status varchar(10),
    constraint Draft_Applicant_fk foreign key (applicantId) references Applicant,
    constraint Draft_Grant_fk foreign key (grantID) references Grant
);

create table Receipt(
    receiptId int auto_increment, 
    evaluatorId varchar(20) not null,
    applicationId int,
    comments clob,
    dateOfEvaluation timestamp,

 constraint Receipt_PK primary key(receiptId),
 constraint Receipt_Evaluator_fk foreign key (evaluatorId) references Evaluator (staffId),
 constraint Receipt_Application_fk foreign key (applicationId) references Application
);


Insert into Evaluator values ('1', 'bob826', 'password', 'Bobby', 'Gee', 'bobster@gmail.com', '027867579', 'Geology');
Insert into Evaluator values ('2', 'susn238', 'password', 'Susan', 'Darcy','sus@gmail.com', '027877579', 'Information Science');
Insert into Evaluator values ('3', 'dan326', 'password', 'Danny', 'Devito', 'warthog@gmail.com', '027863579', 'Geology');


Insert into Grant values('1', 'Petroleum Research Fund', 20000 , 'Geology',  '20200618');
Insert into Grant values('2', 'EFFECT OF E-LEARNING ON STUDENTS Research', 30000 , 'Information Science',  '20200618');
Insert into Grant values('1234', ' Organic carbon sources Research',30000, 'Geology',  '20220618');


Insert into Applicant values ('123', 'bob', 'bob', 'Robert', 'Marley', 'bob@gmail.com', '027867569', 'Geology', 'masters');
Insert into Applicant values ('1', 'hunth66p', 'password', 'Jown', 'Doe', 'jon@gmail.com', '027857689', 'Information Science', 'masters');
Insert into Applicant values ('2', 'hunth55p', 'password', 'Sarah', 'B', 'sarah@gmail.com', '027867669', 'Geology', 'masters');
