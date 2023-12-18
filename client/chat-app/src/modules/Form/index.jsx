import Input from '../../components/input'
import Button from '../../components/button'
import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { authEndpoints } from '../../Api/AuthEndpoints'


const Form = ({
    isSignInPage = false,
}) => {

    const [data, setData] = useState({
        username: '',
        password: ''
    })

    const navigate = useNavigate()

    const handleSubmit = async (e) => {
        console.log("Data :>> ", data)
        e.preventDefault()
        const res = await fetch(authEndpoints[isSignInPage ? 'login' : 'signup'],
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            })
            .then(res => res.json())
            .then(res => {
                localStorage.setItem('user:token', res.token)
                localStorage.setItem('user:details', JSON.stringify(res.member))
                navigate('/')
            })
            .catch(err => {
                console.log("err :>> ", err);
                alert("Invalid Credentials")
            })
    }

    return (
        <div className='bg-light h-screen flex items-center justify-center'>
            <div className=" bg-white w-[600px] h-[800px] shadow-lg rounded-lg flex flex-col justify-center items-center">
                <div className=" text-4xl font-extrabold">Welcome {isSignInPage && 'Back'}</div>
                <div className="text-xl font-light">{isSignInPage ? 'Sign In' : 'Sign Up'}</div>

                <form className='flex flex-col items-center w-full' onSubmit={(e) => handleSubmit(e)}>
                    <Input label="username" name="name" placeholder="Enter your username" className='mb-6' value={data.username} onChange={(e) => setData({ ...data, username: e.target.value })} />
                    <Input label="password" name="password" placeholder="Enter your password" className='mb-14' value={data.password} onChange={(e) => setData({ ...data, password: e.target.value })} />
                    <Button label={isSignInPage ? 'Sign In' : 'Sign Up'} type='submit' className='w-1/2 mb-4' />
                </form>
                <div>
                    {isSignInPage ? "Didn't have an account?" : "Alredy have an account?"}
                    <span className=" text-primary cursor-pointer underline" onClick={() => navigate(`/${isSignInPage ? 'signup' : 'signin'}`)}> {isSignInPage ? 'Sign up' : 'Sign in'} </span>
                </div>
            </div>
        </div>
    )
}

export default Form