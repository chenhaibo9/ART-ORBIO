#include <iostream>
#include<fstream>
#include<iostream>
#include<vector>
#include<utility>
#include<ctime>
#include<cmath>
#include <cstdlib>
#include <windows.h>
#include <algorithm> 
#include"dpt_TestProgram_base.h"
//#define RAND_MAX 0x7fff
using namespace std;


dpt_TestProgram_base::dpt_TestProgram_base()
{
}

dpt_TestProgram_base::~dpt_TestProgram_base()
{
}

///*


 vector<double> dpt_TestProgram_base::Recursive(vector<pair<double,double> >values,int round)
 {

	vector<vector<double> >Testbuffer;
	vector<vector<vector<double> > > TBuffer;
	vector<double>Testbufferx;
	vector<long double> oneTest;
	vector<double> occupants;
	vector<vector<double> > excuteds;

	vector<double> vec;
	vector<vector<double> > tests;
	oneTest.clear();
	vector<double> partsize; 
	vector<vector<pair<double,double> > >ranges;
	vector<pair<double,double> >excldime; 
    vector<vector<pair<double,double > > >excvalues;
	vector<pair<double,double> > range;

	int ct=0; int nofsub; 	
	
	for(int i=0;i<values.size();++i)
	{
		partsize.push_back(abs(values[i].second-values[i].first)/pow(3,round));
	}
	
		for(int i=0;i<values.size();++i)
		{	range.clear();
			double fvalue=values[i].first;
			double svalue=values[i].first+partsize[i];
		  do{	
				pair<double,double> psizes(fvalue,svalue);
				range.push_back(psizes);
				fvalue+=partsize[i];
		   		svalue=fvalue+partsize[i];
				   ct+=1;
			} 
			while(svalue<=values[i].second+0.000000001); nofsub=ct;ct=0;
		ranges.push_back(range);
		}			


vector<int>index;
vector<vector<int> > indexes;

	for(int d=0;d<values.size();++d)
	{
		excldime.clear();index.clear();
		for(int oc=0;oc<excuteds.size();++oc)
		{
			for(int ran=0;ran<3*round;++ran)
			{ 		
				if(excuteds[oc][d]>ranges[d][ran].first&&excuteds[oc][d]<ranges[d][ran].second)
				{	
				 	pair<double,double>cndidate(ranges[d][ran].first,ranges[d][ran].second);
					excldime.push_back(cndidate);
					index.push_back(ran);
				}
			}	
		}
		excvalues.push_back(excldime); indexes.push_back(index);
	}
vector<double> Testcases;


	vector< vector<int> > test(values.size(), vector<int>(excuteds.size()));
	test=indexes; vector<double> vx;vector<double> vy; vector<double>vz; vector<double>vk;

	srand(time(0)); double cx,cy,cz,ck;
	int dime=values.size(); int sx=3,sy=3,sz=3,sk=3; int ktr=0;
	
	srand(time(0));
	
	switch(values.size()){
		case 1: for(sx=3;sx<=pow(3,round);sx+=3)
				{
					for(int x=sx-3;x<sx;x++)
					{
						for(int id=0;id<excuteds.size();++id)
						if(x==indexes[0][id]) goto last;
						cx=rand()/(double)(RAND_MAX/((double)(ranges[0][x].second-ranges[0][x].first)))+ranges[0][x].first;
						Testcases.push_back(cx);
						if(x==sx-1)
						{	
							Testbuffer.push_back(vx);
							vx.clear();
						}
						last:continue; 
					} TBuffer.push_back(Testbuffer);Testbuffer.clear(); 	
				}
				 break;
			 
		case 2: for(sx=3;sx<=pow(3,round);sx+=3)
				{	
					for(sy=3;sy<=pow(3,round);sy+=3)
					{ 
						for(int x=sx-3;x<sx;++x)
						{ 
							for(int y=sy-3;y<sy;++y)
							{
								for(int id=0;id<excuteds.size();++id)
   								if((x==indexes[0][id])&&(y==indexes[1][id])) goto ther;
								
								cx=rand()/(double)(RAND_MAX/((double)(ranges[0][x].second-ranges[0][x].first)))+ranges[0][x].first;
								cy=rand()/(double)(RAND_MAX/((double)(ranges[1][y].second-ranges[1][y].first)))+ranges[1][y].first;
								vx.push_back(cx);
								vy.push_back(cy);
								Testcases.push_back(cx);
								Testcases.push_back(cy);

								ther: continue;
							}Testbuffer.push_back(vx); Testbuffer.push_back(vy); vx.clear(); vy.clear();
						}
					}TBuffer.push_back(Testbuffer);Testbuffer.clear();//<<" group "<<TBuffer.size()<<endl;
				}
				break;
					
		case 3:	for(sx=3;sx<=pow(3,round);sx+=3)
				{	
					for(sy=3;sy<=pow(3,round);sy+=3)
					{	
						for(sz=3;sz<=pow(3,round);sz+=3)
						{
							for(int x=sx-3;x<sx;++x)
							{ 
								for(int y=sy-3;y<sy;++y)
								{
									for(int z=sz-3;z<sz;++z)
									{	
										for(int id=0;id<excuteds.size();++id)
   							    		if(((x==indexes[0][id])&&(y==indexes[1][id]))&&(z==indexes[2][id])) goto here;
										
										cx=rand()/(double)(RAND_MAX/((double)(ranges[0][x].second-ranges[0][x].first)))+ranges[0][x].first;
										cy=rand()/(double)(RAND_MAX/((double)(ranges[1][y].second-ranges[1][y].first)))+ranges[1][y].first;
										cz=rand()/(double)(RAND_MAX/((double)(ranges[2][z].second-ranges[2][z].first)))+ranges[2][z].first;
										Testcases.push_back(cx);
										Testcases.push_back(cy);
										Testcases.push_back(cz);
										vx.push_back(cx);
										vy.push_back(cy);
										vz.push_back(cz);
										here: continue;
									}Testbuffer.push_back(vx); Testbuffer.push_back(vy); Testbuffer.push_back(vz); vx.clear(); vy.clear();vz.clear();
								}
							}
						} TBuffer.push_back(Testbuffer); //Testbuffer.clear(); cout<<" group one"<<endl;
					}
				}
			break;
			
		case 4: for(sx=3;sx<=pow(3,round);sx+=3)
				{	
				for(sy=3;sy<=pow(3,round);sy+=3)
				{	
					for(sz=3;sz<=pow(3,round);sz+=3)
					{
						for(sk=3;sk<=pow(3,round);sk+=3)
						{
							for(int x=sx-3;x<sx;++x)
							{ 
								for(int y=sy-3;y<sy;++y)
								{
									for(int z=sz-3;z<sz;++z)
									{
										for(int k=sk-3;k<sk;++k)
										{	
											for(int id=0;id<excuteds.size();++id)
   						    				if(((x==indexes[0][id])&&(y==indexes[1][id]))&&((z==indexes[2][id])&&(k==indexes[2][id]))) goto end;
											
											cx=rand()/(double)(RAND_MAX/((double)(ranges[0][x].second-ranges[0][x].first)))+ranges[0][x].first;
											cy=rand()/(double)(RAND_MAX/((double)(ranges[1][y].second-ranges[1][y].first)))+ranges[1][y].first;
											cz=rand()/(double)(RAND_MAX/((double)(ranges[2][z].second-ranges[2][z].first)))+ranges[2][z].first;
											ck=rand()/(double)(RAND_MAX/((double)(ranges[3][k].second-ranges[3][k].first)))+ranges[3][k].first;
											
											Testcases.push_back(cx);
											Testcases.push_back(cy);
											Testcases.push_back(cz);
											Testcases.push_back(ck);
										
											end: continue;
										}Testbuffer.push_back(vx); Testbuffer.push_back(vy);Testbuffer.push_back(vz); Testbuffer.push_back(vk); vx.clear(); vy.clear();vz.clear();vk.clear();
									} 
								}
							}
						}
					}
				}TBuffer.push_back(Testbuffer); Testbuffer.clear();
			}
			break;
			
		default: cout<<"";
	}
	

int factor=pow(3,values.size());
int Tesc=pow(factor,round);

vector<int>seq;

int Testnum=values.size();

switch(Testnum)
{
	case 1: 	{
				int arr1[]={0,2,1};
				seq.insert(seq.end(),arr1,arr1+3);
				}break;
				
	case 2:  	{
				int arr2[]={2,16,6,4,14,0,10,12,8}; //2,16,6,4,14,0,10,12,8
				seq.insert(seq.end(),arr2,arr2+9);}
				break;
				
	case 3:		{
				int arr3[]={3,15,18,30,45,57,51,60,27, 12,78,36,38,24,0,66,9,75,54,6,69,33,63,42,21,72,39};
				seq.insert(seq.end(),arr3,arr3+27);
				}
				break;
				
	case 4: 	int arr4[]={0,8,72,84,40,24,28,44,108,52,68,36,4,92,16,104,48,64,80,20,100,56,88,60,32,76,12,96,
						216,272,204,116,296,112,136,168,316,120,252,180,240,128,260,144,284,228,176,304,192,156,292,224,116,
						256,152,312,200,132,236,184,320,268,148,208,288,164,244,140,300,228,124,280,196,220,308,160,276,212,172,264,188};
				seq.insert(seq.end(),arr4,arr4+81);
				break;
	
}

vector<double>Sorted;

int arr=Tesc;

int Rnd=1;
int R=0;

while(arr>=factor)
{

	for(int od=0;od<seq.size();++od)
	{
	for(int sor=0;sor<values.size();++sor) 
	Sorted.push_back(Testcases[R+seq[od]+sor]);
	}
	R+=factor*values.size();	
	arr-=factor;
}

int va=0;
vector<double>Sort;
Sort=Sorted;

while(Rnd<=round-1)
{

	int fact=pow(factor,Rnd);
	Sort.clear();
	for(int i=0;i<fact*values.size();i+=values.size())
	{
	for(int s=0;s<seq.size();++s)
	{ 
	va=seq[s]*fact+i;
	for(int sor=0;sor<values.size();++sor)
	Sort.push_back(Sorted[va+sor]);
	}
	
	}
	Rnd+=1;
}


return Sort;
}





vector<double>dpt_TestProgram_base::ART_Rotate(vector<pair<double,double> > values, int rounds)
{
	int dime=1;
	dime=int(values.size());
	long int k=(2*rounds);
	vector<double>Testbuffer;
	vector<double>Testbufferx;
	vector<double>Testbuffery;

	vector<double> oneTest;
	vector<double> vec;
	vector<vector<double> > tests;
	oneTest.clear();
	vector<double> partsize; vector<vector<pair<double,double> > >ranges;
	vector<pair<double,double> > range;
	int ct=0;
	double buffer[k][dime];
	double newtrl,newt,newtrl1,newtrl2;
	
	for(int i=0;i<values.size();++i)
	{
		partsize.push_back(abs(values[i].second-values[i].first)/(pow(2,rounds)));
	}
	
		for(int i=0;i<values.size();++i)
		{	range.clear();
			double fvalue=values[i].first;
			double svalue=values[i].first+partsize[i];
			do
			{
				pair<double,double> psizes(fvalue,svalue);
				range.push_back(psizes);
				fvalue+=partsize[i];
		   		svalue=fvalue+partsize[i];
			} 
			while(svalue<=values[i].second+0.000000001); random_shuffle(range.begin(),range.begin()+range.size());
			ranges.push_back(range);
		}
		srand(time(0));
		
	switch(dime){
		case 1:  for(int x=0;x<pow(2,rounds);++x)
				 Testbuffer.push_back(rand()/(double)(RAND_MAX/((double)(ranges[0][x].second-ranges[0][x].first)))+ranges[0][x].first);
				 tests.push_back(Testbuffer);
				 break;
			 
		case 2:    //for(int i=0;i<values.size();i++) //for each dimension
				{ 
					for(int x=0;x<pow(2,rounds);++x)
					{
						for(int y=0;y<pow(2,rounds);++y)
						{
						Testbuffer.push_back(rand()/(double)(RAND_MAX/((double)(ranges[0][x].second-ranges[0][x].first)))+ranges[0][x].first);
						Testbuffer.push_back(rand()/(double)(RAND_MAX/((double)(ranges[1][y].second-ranges[1][y].first)))+ranges[1][y].first);
						} 
					}	tests.push_back(Testbuffer);			
				}	
//				for(int sha=0;sha<Testbufferx.size();++sha)
//				{
//					random_shuffle(Testbufferx.begin(),Testbufferx.begin()+Testbufferx.size()-1);
//				}
//				for(int sha=0;sha<Testbufferx.size();++sha)
//				{
//					Testbuffer.push_back(Testbufferx[sha]);
//					Testbuffer.push_back(Testbuffery[sha]);
//					
//				}					
				break;
		case 3:		for(int x=0;x<pow(2,rounds);++x)
					{
						for(int y=0;y<pow(2,rounds);++y)
						{
							for(int z=0;z<pow(2,rounds);++z)
							{
								Testbuffer.push_back(rand()/(double)(RAND_MAX/((double)(ranges[0][x].second-ranges[0][x].first)))+ranges[0][x].first);
								Testbuffer.push_back(rand()/(double)(RAND_MAX/((double)(ranges[1][y].second-ranges[1][y].first)))+ranges[1][y].first);
								Testbuffer.push_back(rand()/(double)(RAND_MAX/((double)(ranges[2][z].second-ranges[2][z].first)))+ranges[2][z].first);
							}
							tests.push_back(Testbuffer);
						}	
					}				
				break;
			
		case 4: 	for(int x=0;x<pow(2,rounds);++x)
					{
						for(int y=0;y<pow(2,rounds);++y)
						{
							for(int z=0;z<pow(2,rounds);++z)
							{
								for(int k=0;k<pow(2,rounds);++k)
								{
									Testbuffer.push_back(rand()/(double)(RAND_MAX/((double)(ranges[0][x].second-ranges[0][x].first)))+ranges[0][x].first);
									Testbuffer.push_back(rand()/(double)(RAND_MAX/((double)(ranges[1][y].second-ranges[1][y].first)))+ranges[1][y].first);
									Testbuffer.push_back(rand()/(double)(RAND_MAX/((double)(ranges[2][z].second-ranges[2][z].first)))+ranges[2][z].first);
									Testbuffer.push_back(rand()/(double)(RAND_MAX/((double)(ranges[3][k].second-ranges[3][k].first)))+ranges[3][k].first);
								}
								tests.push_back(Testbuffer); //random_shuffle(Testbuffer.begin(),Testbuffer.begin()+Testbuffer.size());	
							}	
						}		
					} 
				break;				
			
		default: cout<<"";
	}
/*
	vector<double> testcase;	
	    
		vector< vector<double> > test(values.size(), vector<double>(pow(2,rounds)*2));
		
		for(int j=0;j<pow(2,rounds)*2;++j)
			{
				for(int u=0;u<values.size();++u)
				{
					testcase.push_back(Testbuffer[pow(2,rounds)*2*u+j]);
				}
			}			
*/
		
return Testbuffer;
}

vector<double> dpt_TestProgram_base::FSCS_ART_RE(vector< vector<double> > &excuteds, vector< pair<double, double> > values, vector <double> execdist)
{

		int dimension = values.size();
		vector<double> test; double maxSum = 0;double RmaxSum=0; double distance;

		double pass[dimension];
		const int k=10 ; double cord[dimension];
		vector< vector<double> > candidates;
		vector<double> candist;
		int p=0;
	   vector<double> oneTest;

	if (excuteds.size()%2 == 0) 
	{
		if(excuteds.size()>0)
		{
			vector <vector<double> > mirror;
			mirror.push_back(excuteds.back());
		//	for(int j=0;j<mirror.size();++j)
			oneTest.push_back(mirror[0][1]);
			oneTest.push_back(mirror[0][0]);
			if((dimension-2)==1) oneTest.push_back(mirror[0][2]);
			p+=1;
			return oneTest;
		}
	
		else 
		{
			for (int i = 0; i<dimension; ++i)
			{
				cord[i]=(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);;
				oneTest.push_back(cord[i]);
			}	
			return oneTest; 		
		}
	}
else
	{                                                                                                                                                                                          
		vector<double> test; int ite=0;
		const int k =25; double cord[dimension];
		vector< vector<double> > candidates;
		for (int j = 0; j<k; ++j)
		{
			test.clear();
			
			if(p%2==1){
							
		start:	for (int i = 0; i<dimension; ++i)
			{
				cord[i]=(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);	
				test.push_back(cord[i]);			
			}
			if(cord[0]<cord[1]) goto start;
				}
				
			else
			{							
				there:	for (int i = 0; i<dimension; ++i)
			{
				cord[i]=(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);	
			//	if(cord[i])>values[i].second
				test.push_back(cord[i]);	ite+=1;		
			}
			if(((cord[ite-1]<cord[ite])&&cord[ite]<values[ite-1].second/4)&&(cord[ite]>3*values[ite].second/4)) goto there;
				
			}

			candidates.push_back(test);
			candist.push_back(fabs((sqrt((cord[0]*cord[0])+(cord[1]*cord[1]))+10*(cord[0]+cord[1]))));//+10*cord[0]*cord[1]);
		}
		
		double maxSum = 0;double RmaxSum=0;
		int index = 0,rindex=0;
		for (int j = 0; j<k; ++j)
		{
			double sumLength = 0; double RsumLength=0;
			for (int i = 0;i<execdist.size(); ++i)
			{
				RsumLength+=fabs(execdist[i]-candist[j]);
			}

			if(RmaxSum<RsumLength)
			{
				RmaxSum=RsumLength;
				rindex=j;
			}
		}return candidates[rindex];
	}	
}


//*

///*
vector<double> dpt_TestProgram_base::FSCS_ART_ExR(vector< vector<double> > &excuteds, vector< pair<double, double> > values, vector <double> execdist)
{

		int dimension = values.size();
		vector<double> test; double maxSum = 0;double RmaxSum=0; double distance;

		double pass[dimension];
		double cord[dimension];
		vector< vector<double> > candidates;
		vector<double> candist;
		int p=0;
	   vector<double> oneTest;

	if (excuteds.size()%2 == 0) 
	{
		if(excuteds.size()>0)
		{
			vector <vector<double> > mirror; int t=dimension;
			mirror.push_back(excuteds.back());
			for(int dd=dimension; dd>=2;dd-=2)
			{
				oneTest.push_back(mirror[0][dd]);
				oneTest.push_back(mirror[0][dd-1]); t-=2;	
			}
			if(t=1) oneTest.push_back(mirror[0][0]);
			p+=1;
			return oneTest;
		}
	
		else 
		{
			for (int i = 0; i<dimension; ++i)
			{
				cord[i]=(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);;
				oneTest.push_back(cord[i]);
			}	
			return oneTest; 		
		}
	}
else
	{                                                                                                                                                                                          
		vector<double> test; 
		const int k =25; double cord[dimension]; 
		vector< vector<double> > candidates;
		for (int j = 0; j<k; ++j)
		{
			test.clear();
			
			if(p%2==1){
							
		start:	for (int i = 0; i<dimension; ++i)
			{
				cord[i]=(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);	
				test.push_back(cord[i]);			
			}
			for(int dd=dimension; dd>2;dd-=2)
			if(cord[dimension-1]<cord[dimension]) goto start;
			 }
			 
			 
			else
			{							
				there:	for (int i = 0; i<dimension; ++i)
			{
				cord[i]=(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);	
			//	if(cord[i])>values[i].second
				test.push_back(cord[i]);			
			}
			for(int dd=dimension; dd>2;dd-=2)
			if(((cord[dd]<cord[dd-1])&&cord[dd]<values[dd-1].second/4)&&(cord[1]<0.25*values[dd].second)) goto there;	
			}

			candidates.push_back(test);
		//	for(int dd=dimension; dd>2;dd-=2){
		//	}
			candist.push_back (fabs((sqrt((cord[0]*cord[0])+(cord[1]*cord[1]))+10*(cord[0]+cord[1]))));//+10*cord[0]*cord[1]);
		}
		
		double maxSum = 0;double RmaxSum=0;
		int index = 0,rindex=0;
		for (int j = 0; j<k; ++j)
		{
			double sumLength = 0; double RsumLength=0;
			for (int i = 0;i<execdist.size(); ++i)
			{
				RsumLength+=fabs(execdist[i]-candist[j]);
			}

			if(RmaxSum<RsumLength)
			{
				RmaxSum=RsumLength;
				rindex=j;
			}
		}return candidates[rindex];
	}	
}
//*/

vector<double> dpt_TestProgram_base::FSCS_ART_RR(vector< vector<double> > &excuteds, vector< pair<double, double> > values, vector <double> execdist)
{

		int dimension = values.size();
		vector<double> test; double maxSum = 0;double RmaxSum=0; double distance;

		double pass[dimension];
		const int k=10 ; double cord[dimension];
		vector< vector<double> > candidates;
		vector<double> candist;
		int p=0;
	   vector<double> oneTest;

	if (excuteds.size()%2 == 0) 
	{
		if(excuteds.size()>0)
		{
			vector <vector<double> > mirror;
			mirror.push_back(excuteds.back());
			p+=1;
			
			
			
			if(dimension==1){
			
			double t=(mirror[0][0]>(values[2].first-values[2].second)/2)? ((mirror[0][0])-(values[0].first-values[0].second)/2):((mirror[0][0])+(values[0].second-values[0].first)/2); 
			oneTest.push_back(t);
			goto rt;
			}
			
			if(dimension==2) {
			oneTest.push_back(mirror[0][1]);
			oneTest.push_back(mirror[0][0]);	goto rt;
			}
			
			
			if(dimension==3)
			{
			oneTest.push_back(mirror[0][1]);
			oneTest.push_back(mirror[0][0]);
			double t=(mirror[0][2]>(values[2].first-values[2].second)/2)? ((mirror[0][2])-(values[2].first-values[2].second)/2):((mirror[0][2])+(values[2].second-values[2].first)/2); 
			oneTest.push_back(t);	goto rt;
			}
			
			if(dimension==2) {
			oneTest.push_back(mirror[0][1]);
			oneTest.push_back(mirror[0][0]);
			oneTest.push_back(mirror[0][4]);
			oneTest.push_back(mirror[0][3]);	goto rt;
			}
				
		rt:	return oneTest;
		}
	
		else 
		{
			for (int i = 0; i<dimension; ++i)
			{
				cord[i]=(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);;
				oneTest.push_back(cord[i]);
			}	
			return oneTest; 		
		}
	}
else
	{                                                                                                                                                                                          
		vector<double> test; int size=excuteds.size();
		if(excuteds.size()>10) size=10;
		double ExR=(fabs(values[1].second)-fabs(values[0].first))/excuteds.size();					
		start:	for (int i = 0; i<dimension; ++i)
			{
				cord[i]=(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);	
				test.push_back(cord[i]);			
			}
			if(cord[0]<cord[1]) goto start;
			
			
			distance= fabs(sqrt((cord[0]*cord[0])+(cord[1]*cord[1]))+10*(cord[0]+cord[1]));
				
			for(int d=0;d<size;d++)
			if(distance-execdist[d]>ExR) goto start;
		//	for(int i=0;i<dimension;i++)
			oneTest.push_back(cord[0]);
			if(dimension>1)	oneTest.push_back(cord[1]);
			if(dimension>2) oneTest.push_back(cord[2]);
			if(dimension>3) oneTest.push_back(cord[3]);
			
		} return oneTest;
	}	




///*
vector<double> dpt_TestProgram_base::FSCS_ART(vector< vector<double> > &excuteds, vector< pair<double, double> > values)
{

	int dimension = values.size();   
	cout<<" Entrance "<<endl;
	if (excuteds.size() == 0) 
	{
		vector<double> oneTest; double cord[dimension];
		for (int i = 0; i<dimension; ++i)
		{
			oneTest.push_back(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);
		}
		cout<<" oneTest "<<endl;
		return oneTest;
	}
	
	else
	{      cout<<" calculation of candidate"<<endl;                                                                                                                                                                                                 
		vector<double> test; 
		const int k = 10;
		vector< vector<double> > candidates; 
		for (int j = 0; j<k; ++j)
		{
			test.clear();
			for (int i = 0; i<dimension; ++i)
			{	
				test.push_back(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);
			}
			candidates.push_back(test);
		}
		
	
		double maxSum = 0,RmaxSum=0;
		int index = 0,rindex=0;
		for (int j = 0; j<k; ++j)
		{
			double sumLength = 0;
			for (int i = 0; i<excuteds.size(); ++i)
			{
				double length = 0;
				for (int m = 0; m<dimension; ++m)
					length += (candidates[j][m] - excuteds[i][m])*(candidates[j][m] - excuteds[i][m]);
					sumLength += sqrt(length);
			}
			if (maxSum<sumLength)
			{
				maxSum = sumLength;
				index = j;
			}	
		} return candidates[index];
	}
}

//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


vector< vector<double> > dpt_TestProgram_base::Mirror_ART(vector< vector<double> > excuteds,vector< pair<double, double> > values)
{
	vector< pair<double, double> > newValues(values);
	newValues[0].second=newValues[0].second/2;

	vector<double> oneTest;
	oneTest=FSCS_ART(excuteds, newValues);

	vector< vector<double> > tests;
	tests.push_back(oneTest);

	//使用传递x/2的方法实现镜像
	oneTest[0] +=newValues[0].second-newValues[0].first;
	tests.push_back(oneTest);

	return tests;//返回生成的基于每个分割区域的测试用例，因为输入域被分成两部分，所以一共两个测试用例
}
//*/




/*

vector<double> dpt_TestProgram_base::FSCS_ART(vector< vector<double> > &excuteds, vector< pair<double, double> > values)
{

	int dimension = values.size();//vlues中存储值的取值范围
	//cout<<dimension<<endl;
	//srand((unsigned)(time(0))+timeNum);
	if (excuteds.size() == 0) //如果是第一个，就随机生成
	{
		vector<double> oneTest; //存放一个测试用例
		for (int i = 0; i<dimension; ++i)
		{
			oneTest.push_back(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);
		}
		return oneTest;
	}
	else
	{
		//先随机生成k个候选输入

		vector<double> test;
		const int k = 10; //10个候选输入
		vector< vector<double> > candidates;
		for (int j = 0; j<k; ++j)
		{
			test.clear();
			for (int i = 0; i<dimension; ++i)
			{
				//srand((unsigned)(time(0))); rand()/(double)(/10) 

				test.push_back(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);
			}
			candidates.push_back(test);
		}
		//用例使用最大和标准找到最佳测试
		//笛卡尔距离

		double maxSum = 0;//距离最大值
		int index = 0;//表示第几个候选输入被选为测试用例
		for (int j = 0; j<k; ++j)//k个候选
		{
			double sumLength = 0;
			for (int i = 0; i<excuteds.size(); ++i)//已执行
			{
				double length = 0;
				for (int m = 0; m<dimension; ++m)
					 length += (candidates[j][m] - excuteds[i][m])*(candidates[j][m] - excuteds[i][m]);
				sumLength += sqrt(length);//两个测试用例之间的距离
			}
			if (maxSum<sumLength)
			{
				maxSum = sumLength;
				index = j;
			}
		}
		return candidates[index];
	}
}
//*/
///*
vector<double> dpt_TestProgram_base::randomTest(vector< pair<double, double> > values)
{
	int dimension =values.size();
	vector<double> oneTest; 
	for(int i=0;i<dimension;++i)	
		oneTest.push_back(rand()/(double)(RAND_MAX/((double)(values[i].second-values[i].first))) + values[i].first);
	return oneTest;	
}

//*/
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
/*
vector<double> dpt_TestProgram_base::FSCS_ART_abs(vector< vector<double> > &excuteds, vector< pair<double, double> > values)
{

	int dimension = values.size();
	//cout<<dimension<<endl;
	//srand((unsigned)(time(0))+timeNum);
	if (excuteds.size() == 0) 
	{
		vector<double> oneTest; 
		for (int i = 0; i<dimension; ++i)
		{
	//		cout<<" rand value "<<(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);
			oneTest.push_back(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);
		
		}
		return oneTest;
	}
	else
	{
		
	//	String crossover="false";
		vector<double> test;
		const int k = 10; //
		vector< vector<double> > candidates;
		for (int j = 0; j<k; ++j)
		{
			test.clear();
			for (int i = 0; i<dimension; ++i)
			{
				//srand((unsigned)(time(0))); rand()/(double)(/10) 
				//first point
				test.push_back(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);
			}
			candidates.push_back(test);
		}

		double maxSum = 0;
		int index = 0;
		for (int j = 0; j<k; ++j)//for each candidate
		{
			double sumLength = 0;
			for (int i = 0; i<excuteds.size(); ++i)//for each executed test case,
			{		
				for (int m = 0; m<dimension; ++m) // for each dimension, 
					sumLength += abs(candidates[j][m] - excuteds[i][m]);//calculate distance on each dimension 		
			}								//for each candidate to the executed test case	
			if (maxSum<sumLength)
			{
				maxSum = sumLength;
				index = j;
			}
		}
		return candidates[index];
	}
}
*/

/*

vector<double> dpt_TestProgram_base::FSCS_ART(vector< vector<double> > &excuteds, vector< pair<double, double> > values)
{	//>>>>  FSCS using MAXIMUM-MINIMUM distance <<<<<<<<<<<<<<<<<<

	int dimension = values.size();//vlues中存储值的取值范围
	//cout<<dimension<<endl;
	//srand((unsigned)(time(0))+timeNum);
	if (excuteds.size() == 0) //如果是第一个，就随机生成
	{
		vector<double> oneTest; //存放一个测试用例
		for (int i = 0; i<dimension; ++i)
		{
			oneTest.push_back(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);
		}
		return oneTest;
	}
	else
	{
		//先随机生成k个候选输入

		vector<double> test;
		const int k = 10; //10个候选输入
		vector< vector<double> > candidates;
		for (int j = 0; j<k; ++j)
		{
			test.clear();
			for (int i = 0; i<dimension; ++i)
			{
				//srand((unsigned)(time(0))); rand()/(double)(/10) 

				test.push_back(rand() / (double)(RAND_MAX / ((int)(values[i].second - values[i].first))) + values[i].first);
			}
			candidates.push_back(test);
		}int size=excuteds.size();
		if(excuteds.size()>10) size=10;

		double maxmin = 0; double minsum=sqrt((candidates[0][0] - excuteds[0][0])*(candidates[0][0] - excuteds[0][0]));//距离最大值
		int index = 0;
		for (int j = 0; j<k; ++j)
		{
			double sumLength = 0; 
			for (int i = 0; i<size; ++i)//已执行
			{	
				double length = 0;	
				for (int m = 0; m<dimension; ++m)
				{
					 length += (candidates[j][m] - excuteds[i][m])*(candidates[j][m] - excuteds[i][m]);
					 sumLength += sqrt(length);
				}
				if (sumLength<minsum)
				{
					minsum = sumLength;
				}
			}
			if(minsum>maxmin)
			{	
				maxmin=minsum;
				index=j;	
			}
			minsum=sqrt((candidates[0][0] - excuteds[0][0])*(candidates[0][0] - excuteds[0][0]));		
		}
		return candidates[index];
	}
}

*/



