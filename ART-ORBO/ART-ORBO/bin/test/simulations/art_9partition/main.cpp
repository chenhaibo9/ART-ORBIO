//#include "imitation1.h"
//#include "imitation2.h"
//#include "imitation3.h"
//#include "imitation4.h"
#include"dpt_plgndr.h"
#include"dpt_airy.h"
#include"dpt_bessj.h"
#include"dpt_cel.h"
#include"dpt_golden.h"
#include"dpt_tanh.h"
#include"dpt_probks.h"
#include"dpt_el2.h"
#include"dpt_erfcc.h"
#include"dpt_gammq.h"
#include"dpt_sncndn.h"
#include"dpt_bessj0.h"

#include<fstream>
#include<iostream>
#include<vector>
#include<utility>
#include<ctime>
#include<cmath>
#include <cstdlib>

using namespace std;

void Procedure(dpt_TestProgram_base &object, vector< pair<double, double> > values,ofstream &outfile1, ofstream &outfile2,string message,int testNumber);

void Eaverage(dpt_TestProgram_base &object, vector< pair<double, double> > values, ofstream &outfile1,ofstream &outfile2,string message,int testNumber, int testCounts);

int main()
{
	ofstream outfile1("result1.txt");
	ofstream outfile2("result2.txt");
	int testNumber=100; // the number of tests, which is used to test Em
	int testCounts=100;// the  number of times of test, to get more accurate Fm and Em
	string message="";
//	srand(110);
	srand(time(0));

    dpt_golden object_golden;
	pair<double,double> size_golden1(-100, 60.0);
	pair<double,double> size_golden2(-100, 60.0);
	pair<double,double> size_golden3(-100, 60.0);//��Χ
	vector< pair<double,double> > values_golden;
	values_golden.push_back(size_golden1);
	values_golden.push_back(size_golden2);
	values_golden.push_back(size_golden3);
	message="Golden :  experment program ��test 1000";
	Procedure(object_golden,values_golden,outfile1,outfile2,message,testNumber);
//	Eaverage(object_golden,values_golden,outfile1,outfile2,message,100,testCounts);
	
	
}




void Procedure(dpt_TestProgram_base &object, vector< pair<double, double> > values,
				ofstream &outfile1,ofstream &outfile2,string message,int testNumber)
{
	time_t start, end;
	int number=0,testSum=0,testTimeSum=0;
	outfile1<<message.c_str()<<endl;

	int testCountSum=0,testCountS=0;


	number = 0;
	testSum = 0, testTimeSum = 0;
	
	while(number!=testNumber)
	{	
		int testCount=0; 
	    int rounds=1; vector<double> oneTest;
	    start=clock();

		vector<double> buffer(object.Recursive(values,rounds));
		
		for(int j=0;j<values.size();++j)
		{
			oneTest.push_back(buffer[j]);
		}

		outfile2<<"testCount="<<testCount<<"test value=";
		for(int i=0;i<oneTest.size();++i)
		outfile2<< oneTest[i] << " ";
		outfile2<< endl;
		int counter=0;
		
		while(!object.Produces_Error(oneTest))
		{
			oneTest.clear();
			cout<<"Looping"<<endl;
		    if(buffer.size()<values.size())
			{
				rounds+=1;
				//if(values.size()==4) rounds=4; 	
				buffer=(object.Recursive(values,rounds));	
			}
			
			for(int j=0;j<values.size();++j)
			{
			   oneTest.push_back(buffer[j]);
			}
			
			buffer.erase(buffer.begin(),buffer.begin()+values.size());
			++testCount;
			for(int i=0;i<oneTest.size();++i)
			outfile2<<oneTest[i] << " , ";
			outfile2<< endl;
		}
		end=clock();
		
		//outfile<<number<<"the number of test cases you have used��"<<testCount<<"     the time"<<end-start<<endl;
		++number;
		testSum+=testCount;
		testTimeSum+=end-start;

		if(number==100||number==200||number==500)
			outfile1<<"Recursive "<<number<<"fm :"<<testSum/(double)number<<"  Fm time:" <<testTimeSum/(double)number<<endl;
	}
	outfile1<<"Recursive average number of Test :"<<testSum/(double)testNumber<<"  Fm time:" <<testTimeSum/(double)testNumber<<endl<<endl;



	number = 0;
	testSum = 0, testTimeSum = 0;
	
	while(number!=testNumber)
	{	
		int testCount=0; 
	    int rounds=1; vector<double> oneTest;
	    start=clock();

		vector<double> buffer(object.ART_Rotate(values,rounds));
		
		for(int j=0;j<values.size();++j)
		{
			oneTest.push_back(buffer[j]);
		}

		outfile2<<"testCount="<<testCount<<"test value=";
		for(int i=0;i<oneTest.size();++i)
		outfile2<< oneTest[i] << " ";
		outfile2<< endl;
		int counter=0;
		
		while(!object.Produces_Error(oneTest))
		{
			oneTest.clear();
			cout<<"Looping"<<endl;
		    if(buffer.size()<values.size())
			{
				rounds+=1;
				if(values.size()==4) rounds=4; 	
				buffer=(object.ART_Rotate(values,rounds));	
			}
			
			for(int j=0;j<values.size();++j)
			{
			   oneTest.push_back(buffer[j]);
			}
			
			buffer.erase(buffer.begin(),buffer.begin()+values.size());
			++testCount;
			for(int i=0;i<oneTest.size();++i)
			outfile2<<oneTest[i] << " , ";
			outfile2<< endl;
		}
		end=clock();
		
		//outfile<<number<<"the number of test cases you have used��"<<testCount<<"     the time"<<end-start<<endl;
		++number;
		testSum+=testCount;
		testTimeSum+=end-start;

		if(number==100||number==200||number==500)
			outfile1<<"ART_Rotate "<<number<<"fm :"<<testSum/(double)number<<"  Fm time:" <<testTimeSum/(double)number<<endl;
	}
	outfile1<<"ART_Rotate average number of Test :"<<testSum/(double)testNumber<<"  Fm time:" <<testTimeSum/(double)testNumber<<endl<<endl;



	number=0;
	testSum=0,testTimeSum=0;
	while(number!=testNumber)
	{
		int testCount=1;
	    start=clock();
		vector<double> oneTest(object.randomTest(values));
		outfile2<<"testCount="<<testCount<<"test value=";
			for(int i=0;i<oneTest.size();++i)
				outfile2<< oneTest[i] << " ";
		while(!object.Produces_Error(oneTest) )
		{
			oneTest=object.randomTest(values);
			++testCount;
			outfile2<<"testCount="<<testCount<<"test value=";
			for(int i=0;i<oneTest.size();++i)
				outfile2<< oneTest[i] << " ";
			outfile2<< endl;
		}
		end=clock();
		outfile2<<number<<"the number of test cases being used��"<<testCount<<"     time"<<end-start<<endl;
		++number;
		testSum+=testCount;
		testTimeSum+=end-start;
		if(number==100||number==200||number==500)
			outfile1<<"RT"<<number<<"average Fm:"<<testSum/(double)number<<"  average time:" <<testTimeSum/(double)number<<endl;
	}
	outfile1<<"RT average Fm:"<<testSum/(double)testNumber<<"  average time:" <<testTimeSum/(double)testNumber<<endl;


}


//>>>>>>>>


void Eaverage(dpt_TestProgram_base &object, vector< pair<double, double> > values, ofstream &outfile1,ofstream &outfile2,string message,int testNumber, int testCounts)
{
	time_t start, end;
	outfile1<<message.c_str()<<endl;
	int number=0,testTimeSum=0;
	int failCount=0; //denotes the number of fails

///*

number = 0;
	failCount = 0, testTimeSum = 0;
	
	while(number!=testNumber)
	{	
		int testCount=1;int t=0,rounds=1;
	    start=clock();

		
		vector<double> oneTest;
		
		vector<double> buffer(object.Recursive(values,rounds));
					
			for(int j=0;j<values.size();++j)
			{
			oneTest.push_back(buffer[j]);
			}
		
		while(testCount!=testCounts)//the average times
		{	
			cout<<" failcount "<<endl;		
			if(!object.Produces_Error(oneTest))
			{
				++failCount;
				cout<<testCount<<" ";
			}
			
			oneTest.clear();
			
		    if(buffer.size()<values.size())
			{
				rounds+=1; 
				buffer=(object.Recursive(values,rounds));
			}
			
			for(int j=0;j<values.size();++j)
			{
			   oneTest.push_back(buffer[j]);
			}
			buffer.erase(buffer.begin(),buffer.begin()+values.size());
			
		++testCount;
		}
		
		cout << endl;
		end=clock();

		++number;
		testTimeSum+=end-start;
		if(number==100||number==200||number==500)
			outfile1<<"Recursive tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)number<<"  average time: " <<testTimeSum/(double)number<<endl;
	}
	outfile1<<"Recursive tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)testNumber<<"  average time: " <<testTimeSum/(double)testNumber<<endl;





   number = 0;
	failCount = 0, testTimeSum = 0;
	
	while(number!=testNumber)
	{	
		int testCount=1;int t=0,rounds=1;
	    start=clock();

		
		vector<double> oneTest;
		
		vector<double> buffer(object.ART_Rotate(values,rounds));
					
			for(int j=0;j<values.size();++j)
			{
			oneTest.push_back(buffer[j]);
			}
		
		while(testCount!=testCounts)//the average times
		{	
			cout<<" failcount "<<endl;		
			if(!object.Produces_Error(oneTest))
			{
				++failCount;
				cout<<testCount<<" ";
			}
			
			oneTest.clear();
			
		    if(buffer.size()<values.size())
			{
				rounds+=1; 
				buffer=(object.ART_Rotate(values,rounds));
			}
			
			for(int j=0;j<values.size();++j)
			{
			   oneTest.push_back(buffer[j]);
			}
			buffer.erase(buffer.begin(),buffer.begin()+values.size());
			
		++testCount;
		}
		
		cout << endl;
		end=clock();

		++number;
		testTimeSum+=end-start;
		if(number==100||number==200||number==500)
			outfile1<<"ART_Rotate tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)number<<"  average time: " <<testTimeSum/(double)number<<endl;
	}
	outfile1<<"ART_Rotate tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)testNumber<<"  average time: " <<testTimeSum/(double)testNumber<<endl;


/*
	number = 0;
	failCount = 0, testTimeSum = 0;
	while(number!=testNumber)//the number of test cses to test Em
	{
		int testCount=0;
	    start=clock();
		vector< vector<double> > excuteds; vector<double> execdist;
		vector<double> oneTest;
		while(testCount!=testCounts)//the average times
		{
			oneTest=object.FSCS_ART_ExR(excuteds,values,execdist);

			if(!object.Produces_Error(oneTest))
			{
				++failCount;
				cout<<testCount<<" ";
			}
			excuteds.push_back(oneTest);
			execdist.push_back(fabs(sqrt((oneTest[0]*oneTest[0])+(oneTest[1]*oneTest[1])))+10*(oneTest[0]+oneTest[1]));//+10*oneTest[0]*oneTest[1]));
			++testCount;
		}
		cout << endl;
		end=clock();

		++number;
		testTimeSum+=end-start;
		if(number==100||number==200||number==500)
			outfile1<<"FSCS_ART_ExR tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)number<<"  average time: " <<testTimeSum/(double)number<<endl;
	}
	outfile1<<"FSCS_ART_ExR tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)testNumber<<"  average time: " <<testTimeSum/(double)testNumber<<endl;

*/
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>
 

/*
	number = 0;
	failCount = 0, testTimeSum = 0;
	while(number!=testNumber)//the number of test cses to test Em
	{
		int testCount=0;
	    start=clock();
		vector< vector<double> > excuteds; vector<double> execdist;
		vector<double> oneTest;
		while(testCount!=testCounts)//the average times
		{
			oneTest=object.FSCS_ART_RR(excuteds,values,execdist);

			if(!object.Produces_Error(oneTest))
			{
				++failCount;
				cout<<testCount<<" ";
			}
			excuteds.push_back(oneTest);
			execdist.push_back(fabs(sqrt((oneTest[0]*oneTest[0])+(oneTest[1]*oneTest[1])))+10*(oneTest[0]+oneTest[1]));//+10*oneTest[0]*oneTest[1]));
			++testCount;
		}
		cout << endl;
		end=clock();

		++number;
		testTimeSum+=end-start;
		if(number==100||number==200||number==500)
			outfile1<<"FSCS_ART_RR tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)number<<"  average time: " <<testTimeSum/(double)number<<endl;
	}
	outfile1<<"FSCS_ART_RR tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)testNumber<<"  average time: " <<testTimeSum/(double)testNumber<<endl;



//*/
/*


	number = 0;
	failCount = 0, testTimeSum = 0;
	while(number!=testNumber)//the number of test cses to test Em
	{
		int testCount=0;
	    start=clock();
		vector< vector<double> > excuteds; vector<double> execdist;
		vector<double> oneTest;
		while(testCount!=testCounts)//the average times
		{
			oneTest=object.FSCS_ART_RE(excuteds,values,execdist);

			if(!object.Produces_Error(oneTest))
			{
				++failCount;
				cout<<testCount<<" ";
			}
			excuteds.push_back(oneTest);
			execdist.push_back(fabs(sqrt((oneTest[0]*oneTest[0])+(oneTest[1]*oneTest[1])))+10*(oneTest[0]+oneTest[1]));//+10*oneTest[0]*oneTest[1]));
			++testCount;
		}
		cout << endl;
		end=clock();

		++number;
		testTimeSum+=end-start;
		if(number==100||number==200||number==500)
			outfile1<<"FSCS_ART_RE tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)number<<"  average time: " <<testTimeSum/(double)number<<endl;
	}
	outfile1<<"FSCS_ART_RE tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)testNumber<<"  average time: " <<testTimeSum/(double)testNumber<<endl;

*/
/*
	number = 0;
	failCount = 0, testTimeSum = 0;

	while(number!=testNumber)//the number of test cses to test Em
	{
		int testCount=0;
	    start=clock();
		vector< vector<double> > excuteds;
		vector<double> oneTest;
		while( testCount !=testCounts)//the average times
		{
			oneTest=object.FSCS_ART(excuteds,values);
			if(object.Produces_Error(oneTest))
			{
				++failCount;
				cout<<testCount<<" ";
			}
			excuteds.push_back(oneTest); 
			++testCount;
		}
		cout << endl;
		end=clock();

		++number;
		testTimeSum+=end-start;
		if(number==100||number==200||number==500)
			outfile1<<"FSCS_ART tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)number<<"  average time: " <<testTimeSum/(double)number<<endl;
	}
	outfile1<<"FSCS_ART tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)testNumber<<"  average time: " <<testTimeSum/(double)testNumber<<endl;



//*/
///*

	number=0;
	failCount=0,testTimeSum=0;
	while(number!=testNumber)
	{
		int testCount=0;
	    start=clock();
		vector<double> oneTest;
		while(testCount !=testCounts)
		{
			oneTest=object.randomTest(values);
			if(object.Produces_Error(oneTest))
				++failCount;
			outfile2<<"testCount="<<testCount<<"test value=";
			for(int i=0;i<oneTest.size();++i)
				outfile2<< oneTest[i] << " ";
			outfile2<< endl;
			++testCount;
		}
		end=clock();

		outfile2<<number<<" the number of test cases��"<<testCount<<"     time"<<end-start<<endl;
		++number;
		testTimeSum+=end-start;
		if(number==100||number==200||number==500)
			outfile1<<"RT tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)number<<"  average time: " <<testTimeSum/(double)number<<endl;
	}
	outfile1<<"RT tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)testNumber<<"  average time: " <<testTimeSum/(double)testNumber<<endl<<endl<<endl<<endl;


/*

	number=0;
	failCount=0,testTimeSum=0;
	while(number!=testNumber)
	{
		int testCount=0;
	    start=clock();
		vector< vector<double> > excuteds;//������ִ�в�������
		vector< vector<double> > setTest;
		while(testCount !=testCounts)
		{
			setTest=object.Mirror_ART(excuteds, values);
			for(int i=0;i<setTest.size();++i)
			{
				if(object.Produces_Error(setTest[i]))
					++failCount;
				if(i==0)
					excuteds.push_back(setTest[i]);
				++testCount;
			}
		}
		end=clock();
		++number;
		testTimeSum+=end-start;
		if(number==100||number==200||number==500)
			outfile1<<"Mirror_ART tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)number<<"  average time: " <<testTimeSum/(double)number<<endl;
	}
	outfile1<<"Mirror_ART tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)testNumber<<"  average time: " <<testTimeSum/(double)testNumber<<endl;
//*/




/*
   	number=0;
	failCount=0,testTimeSum=0;
	while(number!=testNumber)//the number of test cses to test Em
	{
		int testCount=0;
	    start=clock();
		vector< vector<double> > excuteds;
		vector<double> execdisted; //vector<double> execdist;
		vector<double> oneTest;
		while( testCount !=testCounts)//the average times
		{
			oneTest=object.Mirror_ART_R(excuteds,values,execdisted);
			if(object.Produces_Error(oneTest))
			{
				++failCount;
				cout<<testCount<<" ";
			}
			//excuteds.push_back(oneTest); //double testdist=oneTest[0].first*oneTest[0].first-oneTest[1].second*oneTest[1].second;
			//execdist.push_back(0.00);
			++testCount;
		}
		cout << endl;
		end=clock();

		++number;
		testTimeSum+=end-start;
		if(number==100||number==200||number==500)
			outfile1<<"Mirror_ART_R tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)number<<"  average time: " <<testTimeSum/(double)number<<endl;

	}
	outfile1<<"Mirror_ART_R tests "<<number<<" times averagely to get "<<testCounts<<" faults:"<<failCount/(double)testNumber<<"  average time: " <<testTimeSum/(double)testNumber<<endl;


/*

	number = 0;
	failCount = 0, testTimeSum = 0;
	while (number != testNumber)
	{
		int testCount = 0;
		start = clock();
		vector< vector<double> > excuteds;
		vector<double> oneTest;
		while (testCount != testCounts)
		{
			oneTest = object.FSCS_ART_abs(excuteds, values);
			if (object.Produces_Error(oneTest))
			{
				++failCount;
				cout << testCount << " ";
			}
			excuteds.push_back(oneTest);
			++testCount;
		}
		cout << endl;
		end = clock();

		++number;
		testTimeSum += end - start;
		if (number == 100 || number == 200|| number == 500)
			outfile1 << "FSCS_ART_abs tests " << number << " times averagely to get " << testCounts << " faults:" << failCount / (double)number << "  even time: " << testTimeSum / (double)number << endl;
	}
	outfile1 << "FSCS_ART_abs tests " << number << " times averagely to get " << testCounts << " faults:" << failCount / (double)testNumber << "  even time: " << testTimeSum / (double)testNumber << endl;

*/




}

