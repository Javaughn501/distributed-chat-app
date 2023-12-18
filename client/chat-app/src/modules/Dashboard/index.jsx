import { useEffect, useState } from 'react'
import Avatar from '../../assets/avatar.svg'
import SendSvg from '../../assets/send.svg'
import PlusSvg from '../../assets/plus.svg'
import Input from '../../components/input'
import { messageEndpoints } from '../../Api/MessageEndpoints'


const Dashboard = () => {

    const contacts = [
        {
            username: 'John',
        },
        {
            username: 'Alexander',
        },
        {
            username: 'Alex',
        },
    ]

    useEffect(() => {
        const token = localStorage.getItem('user:token')
        const loggedInUser = JSON.parse(localStorage.getItem('user:details'))
        const fetchConversations = async () => {
            await fetch(messageEndpoints.getAllChats(loggedInUser?.username), {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
            })
                .then(res => res.json())
                .then(res => {
                    setConversations(res)
                });
        }

        fetchConversations()
    }, [])

    const [user, setUser] = useState(JSON.parse(localStorage.getItem('user:details')))
    const [conversations, setConversations] = useState([])
    console.log('User :>> ', user);

    return (
        <div className='w-screen flex'>
            <div className='w-[25%] bg-secondary' >
                <div className='flex items-center my-8 mx-14'>
                    <div className='border border-primary p-[2px] rounded-full'>
                        <img src={Avatar} width={50} height={50} />
                    </div>
                    <div className='ml-4'>
                        <h3 className='text-2xl'>{user?.username}</h3>
                        <p className='text-lg font-light'>My Account</p>
                    </div>
                </div>
                <hr />
                <div className='mx-14 mt-10'>
                    <div className='text-primary text-lg'>Messages</div>
                    <div>
                        {
                            conversations.map((conversations) => {
                                return (
                                    <div className='flex items-center py-8 border-b border-b-gray-300'>
                                        <div className='cursor-pointer flex items-center'>
                                            <div>
                                                <img src={Avatar} width={60} height={60} />
                                            </div>
                                            <div className='ml-6'>
                                                <h3 className='text-xlg font-semibold'>{conversations.recipientId}</h3>
                                            </div>
                                        </div>
                                    </div>
                                )
                            })
                        }
                    </div>
                </div>
            </div>


            <div className='w-[50%] h-screen bg-white flex flex-col items-center' >
                <div className='w-[75%] bg-secondary h-[80px] mt-14 rounded-full flex items-center px-14'>
                    <div className='cursor-pointer'>
                        <img src={Avatar} width={60} height={60} />
                    </div>
                    <div className='text-lg'>
                        <h3 className='text-lg ml-6'>Alexander</h3>
                    </div>
                    <div></div>
                </div>

                <div className='h-[75%] border w-full overflow-scroll border-b'>
                    <div className='h-[1000px] px-14 py-14 '>
                        <div className='max-w-[40%] bg-secondary rounded-b-lg rounded-tr-lg p-4 mb-6'>
                            Lorem ipsum dolor sit, amet consectetur adipisicing elit. Itaque tempore optio iusto iste dolore nostrum repudiandae autem asperiores dolores commodi! Non veritatis saepe eum quae voluptates atque dolore, velit illum!
                        </div>
                        <div className='max-w-[40%] bg-primary rounded-b-lg rounded-tl-lg ml-auto p-4 text-white mb-6'>
                            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Neque similique et quidem amet perferendis dolorum quibusdam dicta, obcaecati voluptate molestiae dolore ad tempore. Non iure ex, incidunt excepturi corporis vel.
                        </div>
                    </div>
                </div>

                <div className='p-14 w-full flex items-center'>
                    <Input placeholder='Type your message...' className='w-[75%]' inputClassName='p-4 border-0 shadow-nd rounded-full bg-light focus:ring-0 focus:border-0 outline-none ' />
                    <div className='ml-4 p-2 cursor-pointer bg-Light rounded-full'>
                        <img src={SendSvg} />
                    </div>
                    <div className='ml-4 p-2 cursor-pointer bg-Light rounded-full'>
                        <img src={PlusSvg} />
                    </div>
                </div>
            </div>



            <div className='w-[25%] h-screen' >

            </div>
        </div>
    )
}

export default Dashboard