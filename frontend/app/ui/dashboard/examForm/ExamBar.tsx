import { AppBar, Box, Stack, Toolbar, Typography } from "@mui/material";
import React, { useEffect, useRef, useState } from "react";
import Timer from "../timer/timer";

const ExamBar = () => {
  const [isFixed, setIsFixed] = useState(false);
  const appBarRef = useRef<HTMLDivElement>(null);
  const boxRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleScroll = () => {
      if (appBarRef.current) {
        const appBarTop = appBarRef.current.getBoundingClientRect().top;
        const boxTop = boxRef.current?.getBoundingClientRect().top;

        if (window.scrollY > boxTop) {
          setIsFixed(true);
        } else {
          setIsFixed(false);
        }
      }
    };

    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);
  return (
    <>
      <Box
        sx={{
          margin: { md: 4 },
          marginY: { xs: 2, md: 0 },
        }}
        ref={boxRef}
      >
        <AppBar
          ref={appBarRef}
          position={isFixed ? "fixed" : "relative"}
          sx={{
            borderRadius: 3,
            opacity: 0.85,
            backgroundColor: "#e7e5e4",
            // padding: 4,
            marginY: 2,
            // marginLeft:5,
            // marginRight:5,
            top: isFixed ? 0 : "auto",
            zIndex: isFixed ? 1100 : "auto",
            transition: "position 0.3s ease-in-out",
          }}
        >
          <Toolbar sx={{}}>
            <Box sx={{ flexGrow: 1 }}>
              <Stack
                direction="row"
                spacing={2}
                alignItems="center"
                justifyContent="space-between"
              >
                <Typography variant="body1" fontWeight="bold" color="black">
                  Questions answered:
                </Typography>

                <Timer initialTime={200} />
              </Stack>
            </Box>
          </Toolbar>
        </AppBar>
      </Box>
    </>
  );
};

export default ExamBar;