"use client";

import routes from '@/app/routes';
import CompletedExam from '@/app/ui/dashboard/exam-completed/CompletedExam';
import CompletedExamDataType from '@/app/ui/dashboard/exam-completed/types/CompletedExamDataType';
import { Container } from '@mui/material';
import axios from 'axios';
import { useSession } from 'next-auth/react';
import Error from 'next/error';
import { useRouter } from 'next/router';
import React, { useCallback, useEffect, useState } from 'react'

const Page = ({ params } : any) => {
  // const router = useRouter();
  // const { id } = router.query;
  const {data: session, status} = useSession();
  const id = params.id;
  const [data, setData] = useState<CompletedExamDataType>({});
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<any>(null);
  // console.log(id);
  const fetchData = useCallback(async ()=> {
    try{
      setLoading(true);
      console.log("calling")

      const acstoken =
        status === "authenticated" ? `Bearer ${session.token}` : "";

      console.log(acstoken);
      const hdr =
        acstoken == "" ? {} : { headers: { Authorization: `${acstoken}` } };
      const response = await axios.get(routes.getExamById, {
        ...hdr,
        params: {
          id: id,
        },
      });
      console.log(response.data);
      setData(response.data);

    } catch (error : any){
      console.log(error.message);
      setError(error.message);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    fetchData();
  },[]);

  return (
    <>
      <Container >

      <CompletedExam info={data.examWithoutUserDTO} ques={data.questionWithoutExams} />

      </Container>
    </>
  )
}

export default Page